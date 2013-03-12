package com.barchart.missive.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
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
import com.barchart.missive.util.ClassUtil;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public abstract class Missive implements TagMap {

	/*
	 * Index value for empty array entry.
	 */
	private static final int EMPTY_ENTRY = -1;

	private static final AtomicInteger classCount = new AtomicInteger(0);

	/* ClassCode, TagCode */
	protected static volatile int[][] indexRegistry = new int[0][];
	protected static volatile Tag<?>[][] tagRegistry = new Tag<?>[0][];

	private final static ConcurrentMap<Class<? extends Missive>, Integer> classMap = 
			new ConcurrentHashMap<Class<? extends Missive>, Integer>();

	/* Instance variables */
	private volatile int classCode;
	private volatile Object[] values;

	/**
	 * Builds a new missive of the specified class with null values. 
	 * 
	 * @param clazz
	 * @return
	 */
	public static <V extends Missive> V build(final Class<V> clazz) {

		V missive = null;
		try {
			final Constructor<V> c = clazz.getDeclaredConstructor();
			c.setAccessible(true);
			missive = c.newInstance();
		} catch (final Exception e1) {
			throw new MissiveException(e1);
		}

		if (!classMap.containsKey(clazz)) {
			throw new MissiveException(clazz.getName()
					+ " was never installed.");
		}

		final int clazzCode = classMap.get(clazz);

		missive.classCode = clazzCode;
		missive.values = new Object[tagRegistry[clazzCode].length];

		return missive;
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
	public static <V extends Missive> V build(final Class<V> clazz, 
			final Map<Tag, Object> map) {
		
		V missive = build(clazz);
		
		for(final Entry<Tag, Object> e : map.entrySet()) {
			if(missive.contains(e.getKey())) {
				missive.set(e.getKey(), e.getKey().cast(e.getValue()));
			}
		}
		
		return missive;
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
	public static <V extends Missive, W extends V> V build(final Class<V> base,
			final Class<W> sub,	final Map<Tag, Object> map) {
		
		final V missive = build(base);
		final W subMiss = build(sub, map);
		
		missive.values = subMiss.values;
		
		return missive;
		
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
	public static <V extends Missive> V build(final Class<V> clazz, final TagMap map) {
		
		V missive = build(clazz);
		
		for(Tag t : map.tags()) {
			if(missive.contains(t)) {
				missive.set(t, map.get(t));
			}
		}
		
		return missive;
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
	public static <V extends Missive, W extends V> V build(final Class<V> base, final Class<W> sub, 
			final TagMap map) {
		
		final V missive = build(base);
		final W subMiss = build(sub, map);
		
		missive.values = subMiss.values;
		
		return missive;

		
	}
	
	/**
	 * This method must be called in a static context from all classes
	 * extending Missive.  
	 * 
	 * @param tags
	 */
	@SuppressWarnings("unchecked")
	protected static void install(Tag<?>[] tags) {

		if (tags == null) {
			tags = new Tag<?>[0];
		}

		/* Get current class object */
		final Class<?>[] trace = new ClassUtil.ClassTrace().getClassContext();
		final Class<? extends Missive> current = (Class<? extends Missive>) trace[2];
		Class<?> superClazz = current.getSuperclass();

		/* Build set of tags of all super classes */
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();

		for (final Tag<?> t : tags) {
			tagSet.add(t);
		}

		/* Build list of user declared super classes */
		Class<?> tempSuperClass = superClazz;
		List<Class<?>> superClassList = new ArrayList<Class<?>>();
		while (Missive.class.isAssignableFrom(tempSuperClass)
				&& !tempSuperClass.equals(Missive.class)
				&& !tempSuperClass.equals(MissiveSafe.class)) {

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
				indexRegistry[classCode][tag.index()]++;
			}
		}
		
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

	/**
	 * Pass through method for MissiveSafe
	 */
	protected <V> void set(final Tag<V> tag, final V value)
			throws MissiveException {
		values[indexRegistry[classCode][tag.index()]] = value;
	}

	/* ***** ***** Begin public methods ***** ***** */

	/**
	 * If a missive is masking the values of a subclass, this
	 * method returns the subclass with all the values of the 
	 * calling missive.
	 * 
	 * @param newClass
	 * @return
	 */
	public <M extends Missive> M cast(final Class<M> newClass) {
		
		if(!this.getClass().isAssignableFrom(newClass)) {
			throw new MissiveException("Class " + newClass.getName() + 
					" must be subclass to cast to " + this.getClass().getName());
		}
		
		final M newMissive = build(newClass);
		newMissive.values = values;
		
		return newMissive;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) throws MissiveException {
		return (V) values[indexRegistry[classCode][tag.index()]];
	}

	@Override
	public boolean contains(final Tag<?> tag) {
		return indexRegistry[classCode][tag.index()] != EMPTY_ENTRY;
	}

	@Override
	public Tag<?>[] tags() {
		return tagRegistry[classCode];
	}

	@Override
	public int size() {
		return tagRegistry[classCode].length;
	}
	
	/*
	 * TODO Review Missive equality 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean equals(final Object o) {
		
		if(o == null) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(!(o instanceof Missive)) {
			return false;
		}
		
		final Missive m = (Missive)o;
		
		if(size() != m.size()) {
			return false;
		}
		
		for(Tag t : tags()) {
			
			if(!m.contains(t)) {
				return false;
			}
			
			if(m.get(t) == null) {
				if(get(t) != null) {
					return false;
				}
			}
			
			if(get(t) == null) {
				return false;
			}
			
			if(t.isList()) {
				if(!compareCollections((Collection<Object>)get(t), 
						(Collection<Object>)m.get(t))) {
					return false;
				}
			}
			
			if(!get(t).equals(m.get(t))) {
				return false;
			}
			
		}
		
		return true;
	}
	
	private static boolean compareCollections(final Collection<Object> thisC, 
			final Collection<Object> thatC) {
		
		if(thisC == null || thatC == null) {
			return false;
		}
		
		if(thisC.size() != thatC.size()) {
			return false;
		}
		
		for(final Object o : thisC) {
			
			if(!thatC.contains(o)) {
				return false;
			}
			
		}
		
		return true;
	}

}
