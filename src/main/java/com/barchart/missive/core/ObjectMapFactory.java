package com.barchart.missive.core;

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
 * 
 * @author Gavin M Litchfield
 * 
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
	protected static volatile int[] valArraySizes = new int[0];

	private final static ConcurrentMap<Class<? extends ObjectMap>, Integer> classMap = 
			new ConcurrentHashMap<Class<? extends ObjectMap>, Integer>();

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

		map.init();
		
		return map;
	}

	/**
	 * Build a new missive of the specified class, to be populated
	 * with values from the map parameter.  Any values not members of
	 * the specified missive type will be ignored. 
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends ObjectMap> V build(final Class<V> clazz, 
			final Map<Tag, Object> map) {
		
		V newmap = build(clazz);
		
		for(final Entry<Tag, Object> e : map.entrySet()) {
			if(newmap.contains(e.getKey())) {
				newmap.set(e.getKey(), e.getKey().cast(e.getValue()));
			}
		}
		
		return newmap;
	}
	
	/**
	 * Build a new missive of the specified class, to be populated
	 * with values from the map parameter.  All tags present in the subclass
	 * W will be included.
	 * 
	 * @param base
	 * @param sub
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <V extends ObjectMap, W extends V> V build(final Class<V> base,
			final Class<W> sub,	final Map<Tag, Object> map) {
		
		final V newMap = build(base);
		final W subMap = build(sub, map);
		
		newMap.values = subMap.values;
		
		return newMap;
		
	}
	
	/**
	 * Build a new missive of the specified class, to be populated
	 * with values from the map parameter.  Any values not members of
	 * the specified missive type will be ignored. 
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends ObjectMap> V build(final Class<V> clazz, final TagMap map) {
		
		V newMap = build(clazz);
		
		for(Tag t : map.tags()) {
			if(newMap.contains(t)) {
				newMap.set(t, map.get(t));
			}
		}
		
		return newMap;
	}
	
	/**
	 * Build a new missive of the specified class, to be populated
	 * with values from the map parameter.  All tags present in the subclass
	 * W will be included.
	 * 
	 * @param base
	 * @param sub
	 * @param map
	 * @return
	 */
	public static <V extends ObjectMap, W extends V> V build(final Class<V> base, 
			final Class<W> sub,	final TagMap map) {
		
		final V newMap = build(base);
		final W subMap = build(sub, map);
		
		newMap.values = subMap.values;
		
		return newMap;
		
	}

	/**
	 * 
	 * @param manifest
	 */
	public static void install(final Manifest<ObjectMap> manifest) {
		
		//block
		
		for(Class<? extends ObjectMap> clazz : manifest.orderedClasses()) {
			install(clazz, manifest.get(clazz));
		}
		
	}
	
	private static void install(Class<? extends ObjectMap> current, Tag<?>[] tags) {

		if (tags == null) {
			tags = new Tag<?>[0];
		}

		Class<?> superClazz = current.getSuperclass();
		
		/* Build set of tags of all super classes */
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();

		for (final Tag<?> t : tags) {
			tagSet.add(t);
		}

		/* Build list of user declared super classes */
		Class<?> tempSuperClass = superClazz;
		List<Class<?>> superClassList = new ArrayList<Class<?>>();
		while (ObjectMap.class.isAssignableFrom(tempSuperClass)
				&& !tempSuperClass.equals(ObjectMap.class)
				&& !tempSuperClass.equals(ObjectMapSafe.class)) {

			superClassList.add(tempSuperClass);
			tempSuperClass = tempSuperClass.getSuperclass();

		}
		
		List<Tag<?>> newTagList = null;
		Tag<?>[] newTagArray = null;
		if(!superClassList.isEmpty()) {
			
			/* Retrieve tag array of superclass */
			Tag<?>[] superclassTags = new Tag<?>[0];
			try {
				superclassTags = tagRegistry[classMap.get(superClazz)];
			} catch (final Exception e) {
				throw new MissiveException(e);
			}
			
			/* Remove all tags in superclass from subclass's tag set */
			for(final Tag<?> t : superclassTags) {
				if(tagSet.contains(t)) {
					tagSet.remove(t);
				}
			}
			
			/* Sort new tags and determine if any must be inserted in super classes */
			newTagList = new ArrayList<Tag<?>>(tagSet);
			Collections.sort(newTagList);
			
			for(final Tag<?> tag : newTagList) {
				for(final Class<?> clazz : superClassList) {
					insertTagsInSuperclass(clazz, tag);
				}
			}
			
			/* 
			 * Copy superclass tags into subclass then add on new tags.  Note:
			 * while the index registry is updated to maintain ordering of tag
			 * indexes, the tag registry for a class is the tag registry of its
			 * super class concatenated with any new tags because there are no
			 * order requirements. 
			 */
			newTagArray = new Tag<?>[superclassTags.length + newTagList.size()];
			System.arraycopy(superclassTags, 0, newTagArray, 0, superclassTags.length);
			System.arraycopy(newTagList.toArray(new Tag<?>[0]), 0, newTagArray, 
					superclassTags.length, newTagList.size());
			
		} else {
			/* Installed class is a direct subclass of Missive */
			newTagList = new ArrayList<Tag<?>>(tagSet);
			Collections.sort(newTagList);
			newTagArray = newTagList.toArray(new Tag<?>[0]);
		}
		
		/* Build new tag array and update tag registry */
		final Tag<?>[][] newTagRegistry = new Tag<?>[classCount.get() + 1][];
		System.arraycopy(tagRegistry, 0, newTagRegistry, 0, classCount.get());
		newTagRegistry[classCount.get()] = newTagArray;
		tagRegistry = newTagRegistry;

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
		if(!superClassList.isEmpty()) {
			System.arraycopy(indexRegistry[classMap.get(superClazz)], 0, newIndexes, 
					0, TagFactory.maxIndex());
		}
		
		/* Set indexes for all new tags */
		int counter = 0;
		if(!superClassList.isEmpty()) {
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
		
	}
	
	/*
	 * If a newly installed Missive has tags which have lower indexes than
	 * its super class, the index registry of all user defined super classes
	 * must be padded to allow a super class to be cast to a subclass without
	 * reordering the object array.
	 */
	private static void insertTagsInSuperclass(final Class<?> clazz, final Tag<?> tag) {
		
		if(!classMap.containsKey(clazz)) {
			throw new MissiveException("Class " + clazz.getName() + " not in registry");
		}
		
		final int classCode = classMap.get(clazz);
		
		final Tag<?>[] tagArray = tagRegistry[classCode];
		
		for(final Tag<?> t : tagArray) {
			if(t.index() > tag.index()) {
				indexRegistry[classCode][t.index()]++;
			}
		}
		
		valArraySizes[classCode]++;
		
	}

	/**
	 * Called from TagFactory upon new tag creation, this method pads 
	 * the index registry for all currently registered missive types.
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
