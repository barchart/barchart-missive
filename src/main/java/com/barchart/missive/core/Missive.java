package com.barchart.missive.core;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.barchart.missive.api.Tag;
import com.barchart.missive.util.ClassUtil;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public abstract class Missive implements TagMap {

	/**
	 * Index value for empty array entry.
	 */
	private static final int EMPTY_ENTRY = -1;

	private static final AtomicInteger classCount = new AtomicInteger(0);

	/** ClassCode, TagCode */
	protected static volatile int[][] indexRegistry = new int[0][];
	protected static volatile Tag<?>[][] tagRegistry = new Tag<?>[0][];

	private final static ConcurrentMap<Class<? extends Missive>, Integer> classMap = 
			new ConcurrentHashMap<Class<? extends Missive>, Integer>();

	private volatile int classCode;
	private volatile Object[] values;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends Missive> V build(final Class<V> clazz, 
			final Map<Tag, Object> map) {
		
		V v = build(clazz);
		
		for(Entry<Tag, Object> e : map.entrySet()) {
			if(v.contains(e.getKey())) {
				v.set(e.getKey(), e.getKey().cast(e.getValue()));
			}
		}
		
		return v;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends Missive> V build(final Class<V> clazz, final TagMap map) {
		
		V v = build(clazz);
		
		for(Tag t : map.tags()) {
			if(v.contains(t)) {
				v.set(t, map.get(t));
			}
		}
		
		return v;
	}
	
	@SuppressWarnings("unchecked")
	protected static void install(Tag<?>[] tags) {

		if (tags == null) {
			tags = new Tag<?>[0];
		}

		/** Get current class object */
		final Class<?>[] trace = new ClassUtil.ClassTrace().getClassContext();
		final Class<? extends Missive> current = (Class<? extends Missive>) trace[2];
		Class<?> superClazz = current.getSuperclass();

		/** Build set of tags of all superclasses */
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();

		for (final Tag<?> t : tags) {
			tagSet.add(t);
		}

		while (Missive.class.isAssignableFrom(superClazz)
				&& !superClazz.equals(Missive.class)
				&& !superClazz.equals(MissiveSafe.class)) {

			Tag<?>[] classTags = new Tag<?>[0];
			try {
				classTags = tagRegistry[classMap.get(superClazz)];
			} catch (final Exception e) {
				throw new MissiveException(e);
			}

			for (final Tag<?> t : classTags) {
				tagSet.add(t);
			}

			superClazz = superClazz.getSuperclass();

		}

		/** Build new tag array and update tag registry */
		final Tag<?>[] newTags = new Tag<?>[tagSet.size()];
		int counter = 0;
		for (final Tag<?> t : tagSet) {
			newTags[counter] = t;
			counter++;
		}

		final Tag<?>[][] newTagRegistry = new Tag<?>[classCount.get() + 1][];
		System.arraycopy(tagRegistry, 0, newTagRegistry, 0, classCount.get());
		newTagRegistry[classCount.get()] = newTags;
		tagRegistry = newTagRegistry;

		/** Build new index array and update index registry */
		final int[] newIndexes = new int[TagFactory.maxIndex()];
		for (int i = 0; i < TagFactory.maxIndex(); i++) {
			newIndexes[i] = EMPTY_ENTRY;
		}
		counter = 0;
		for (final Tag<?> t : tagSet) {
			newIndexes[t.index()] = counter;
			counter++;
		}

		final int[][] newIndexRegistry = new int[classCount.get() + 1][];
		System.arraycopy(indexRegistry, 0, newIndexRegistry, 0,
				classCount.get());
		newIndexRegistry[classCount.get()] = newIndexes;
		indexRegistry = newIndexRegistry;

		/** Assign new class code and update counter */
		classMap.put(current, classCount.getAndIncrement());

	}

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

	/* Begin public methods */

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
