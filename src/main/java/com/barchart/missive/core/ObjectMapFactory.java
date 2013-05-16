package com.barchart.missive.core;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;

/**
 * @author Gavin M Litchfield
 */
public final class ObjectMapFactory {

	/*
	 * Index value for empty array entry.
	 */
	static final int EMPTY_ENTRY = -1;

	private static final AtomicInteger classCount = new AtomicInteger(0);

	/* ClassCode, TagCode */
	protected static volatile int[][] indexRegistry = new int[0][];
	protected static volatile Tag<?>[][] tagRegistry = new Tag<?>[0][];

	/** Ordered from lowest subclass to highest superclass before ObjectMap */
	protected static volatile int[][] classHierarchy = new int[0][];
	protected static volatile int[] valArraySizes = new int[0];

	@SuppressWarnings("unchecked")
	protected static volatile Class<? extends ObjectMap>[] classes = new Class[0];

	final static ConcurrentMap<Class<? extends ObjectMap>, Integer> classMap = new ConcurrentHashMap<Class<? extends ObjectMap>, Integer>();

	private ObjectMapFactory() {
	}

	/**
	 * Builds a new missive of the specified class with null values.
	 * 
	 * @param clazz
	 * @return
	 */
	public static <V extends ObjectMap> V build(final Class<V> clazz) {

		V map = null;
		try {
			// TODO Make static reference to constructors
			final Constructor<V> c = clazz.getDeclaredConstructor();
			c.setAccessible(true);
			map = c.newInstance();
		} catch (final Exception e1) {
			throw new MissiveException(e1);
		}

		if (!classMap.containsKey(clazz)) {
			throw new MissiveException(clazz.getName()
					+ " was never installed.");
		}

		final int clazzCode = classMap.get(clazz);

		map.classCode = clazzCode;
		map.values = new Object[valArraySizes[clazzCode]];
		map.childClass = clazz;
		map.classHierarchy = classHierarchy[clazzCode];
		map.pos = 0;

		/* Initialize map */
		map.init();

		return map;
	}

	/**
	 * Build a new missive of the specified class, to be populated with values
	 * from the map parameter. Any values not members of the specified missive
	 * type will be ignored.
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends ObjectMap> V build(final Class<V> clazz,
			final Map<Tag, Object> map) {

		final V newmap = build(clazz);

		for (final Entry<Tag, Object> e : map.entrySet()) {
			if (newmap.contains(e.getKey())) {
				newmap.set(e.getKey(), e.getKey().cast(e.getValue()));
			}
		}

		return newmap;
	}

	/**
	 * Build a new missive of the specified class, to be populated with values
	 * from the map parameter. All tags present in the subclass W will be
	 * included.
	 * 
	 * @param base
	 * @param sub
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <V extends ObjectMap, W extends V> V build(
			final Class<V> base, final Class<W> sub, final Map<Tag, Object> map) {

		final W subMap = build(sub, map);

		subMap.classCode = classMap.get(base);

		return subMap;

	}

	/**
	 * Build a new missive of the specified class, to be populated with values
	 * from the map parameter. Any values not members of the specified missive
	 * type will be ignored.
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends ObjectMap> V build(final Class<V> clazz,
			final TagMap map) {

		final V newMap = build(clazz);

		for (final Tag t : map.tagsList()) {
			if (newMap.contains(t)) {
				newMap.set(t, map.get(t));
			}
		}

		return newMap;
	}

	/**
	 * Build a new missive of the specified class, to be populated with values
	 * from the map parameter. All tags present in the subclass W will be
	 * included.
	 * 
	 * @param base
	 * @param sub
	 * @param map
	 * @return
	 */
	public static <V extends ObjectMap, W extends V> V build(
			final Class<V> base, final Class<W> sub, final TagMap map) {

		final W subMap = build(sub, map);

		subMap.classCode = classMap.get(base);

		return subMap;

	}

	/**
	 * 
	 * @param manifest
	 */
	public synchronized static void install(final Manifest<ObjectMap> manifest) {

		for (final Class<? extends ObjectMap> clazz : manifest.orderedClasses()) {
			if (!classMap.containsKey(clazz)) {
				install(clazz, manifest.get(clazz));
			}
		}

	}

	private static void install(final Class<? extends ObjectMap> current,
			Tag<?>[] tags) {

		if (tags == null) {
			tags = new Tag<?>[0];
		}

		final Class<?> superClazz = current.getSuperclass();

		/* Build set of tags of all super classes */
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();

		for (final Tag<?> t : tags) {
			tagSet.add(t);
		}

		/* Build list of user declared super classes */
		Class<?> tempSuperClass = superClazz;
		final List<Class<?>> superClassList = new ArrayList<Class<?>>();
		while (ObjectMap.class.isAssignableFrom(tempSuperClass)
				&& !tempSuperClass.equals(ObjectMap.class)
				&& !tempSuperClass.equals(ObjectMapSafe.class)) {

			superClassList.add(tempSuperClass);
			tempSuperClass = tempSuperClass.getSuperclass();

		}

		List<Tag<?>> newTagList = null;
		Tag<?>[] newTagArray = null;
		if (!superClassList.isEmpty()) {

			/* Retrieve tag array of superclass */
			Tag<?>[] superclassTags = new Tag<?>[0];
			try {
				superclassTags = tagRegistry[classMap.get(superClazz)];
			} catch (final Exception e) {
				throw new MissiveException(e);
			}

			/* Remove all tags in superclass from subclass's tag set */
			for (final Tag<?> t : superclassTags) {
				if (tagSet.contains(t)) {
					tagSet.remove(t);
				}
			}

			/*
			 * Sort new tags and determine if any must be inserted in super
			 * classes
			 */
			newTagList = new ArrayList<Tag<?>>(tagSet);
			Collections.sort(newTagList);

			/*
			 * Copy superclass tags into subclass then add on new tags.
			 */
			newTagArray = new Tag<?>[superclassTags.length + newTagList.size()];
			System.arraycopy(superclassTags, 0, newTagArray, 0,
					superclassTags.length);
			System.arraycopy(newTagList.toArray(new Tag<?>[0]), 0, newTagArray,
					superclassTags.length, newTagList.size());

		} else {
			/* Installed class is a direct subclass of ObjectMap */
			newTagList = new ArrayList<Tag<?>>(tagSet);
			Collections.sort(newTagList);
			newTagArray = newTagList.toArray(new Tag<?>[0]);
		}

		/* Build new tag array and update tag registry */
		tagRegistry = grow(tagRegistry);
		tagRegistry[classCount.get()] = newTagArray;

		/* Add size entry to size array */
		final int[] newSizes = new int[classCount.get() + 1];
		System.arraycopy(valArraySizes, 0, newSizes, 0, classCount.get());
		newSizes[classCount.get()] = newTagArray.length;
		valArraySizes = newSizes;

		/* Build new index array and update index registry */
		final int[] newIndexes = new int[TagFactory.maxIndex()];
		for (int i = 0; i < TagFactory.maxIndex(); i++) {
			newIndexes[i] = EMPTY_ENTRY;
		}

		/* Copy super class indexes */
		if (!superClassList.isEmpty()) {
			System.arraycopy(indexRegistry[classMap.get(superClazz)], 0,
					newIndexes, 0, TagFactory.maxIndex());
		}

		/* Set indexes for all new tags */
		int counter = 0;
		if (!superClassList.isEmpty()) {
			counter = tagRegistry[classMap.get(superClazz)].length;
		}
		for (final Tag<?> t : newTagList) {
			newIndexes[t.index()] = counter;
			counter++;
		}

		final int[][] newIndexRegistry = new int[classCount.get() + 1][];
		System.arraycopy(indexRegistry, 0, newIndexRegistry, 0,
				classCount.get());
		newIndexRegistry[classCount.get()] = newIndexes;
		indexRegistry = newIndexRegistry;

		/* Assign new class code and update counter */
		classMap.put(current, classCount.getAndIncrement());
		final int curClassCode = classMap.get(current);

		/* Build class hierarchy */
		final int[] hierarchy = new int[superClassList.size() + 1];
		hierarchy[0] = curClassCode;
		counter = 1;
		for (final Class<?> clazz : superClassList) {
			hierarchy[counter] = classMap.get(clazz);
			counter++;
		}

		/* Append new hierarchy */
		classHierarchy = grow(classHierarchy);
		classHierarchy[curClassCode] = hierarchy;

		/* Append new class */
		classes = grow(classes);
		classes[curClassCode] = current;
	}

	@SuppressWarnings("unchecked")
	private static <V> V[] grow(final V[] v) {
		final V[] newV = (V[]) Array.newInstance(v.getClass()
				.getComponentType(), v.length + 1);
		System.arraycopy(v, 0, newV, 0, v.length);
		return newV;
	}

	/**
	 * Called from TagFactory upon new tag creation, this method pads the index
	 * registry for all currently registered missive types.
	 */
	protected static synchronized void incrementIndexRegistry() {

		final int oldSize = TagFactory.maxIndex();

		for (int i = 0; i < indexRegistry.length; i++) {
			final int[] newIndexes = new int[oldSize + 1];
			System.arraycopy(indexRegistry[i], 0, newIndexes, 0, oldSize);
			indexRegistry[i] = newIndexes;
			indexRegistry[i][oldSize] = EMPTY_ENTRY;
		}

	}

}
