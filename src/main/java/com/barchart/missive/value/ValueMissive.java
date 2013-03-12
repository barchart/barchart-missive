package com.barchart.missive.value;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.util.ClassUtil;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public abstract class ValueMissive {
	
	/**
	 * Index value for empty array entry.
	 */
	private static final int EMPTY_ENTRY = -1;

	private static final AtomicInteger classCount = new AtomicInteger(0);

	/** ClassCode, TagCode */
	protected static volatile int[] byteSizeRegistry = new int[0];
	protected static volatile int[][] indexRegistry = new int[0][];
	protected static volatile ValueTag<?>[][] tagRegistry = new ValueTag<?>[0][];

	private final static ConcurrentMap<Class<? extends ValueMissive>, Integer> classMap = 
			new ConcurrentHashMap<Class<? extends ValueMissive>, Integer>();

	/* Instance variables */
	private volatile int classCode;
	volatile byte[] bytes;
	
	/* ***** ***** Begin public static methods ***** ***** */
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static <V extends ValueMissive> V build(final Class<V> clazz) {

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
		missive.bytes = new byte[byteSizeRegistry[clazzCode]];
		
		return missive;
	}
	
	/* ***** ***** Begin non-public static methods ***** ***** */

	/*
	 * Used by MissiveType to handle get.  Calls which retrieve a missive
	 * nested inside another missive build a new missive of the specified
	 * type and populate its byte array.
	 */
	static <V extends ValueMissive> V build(final Class<V> clazz,
			final byte[] bytes, final int index) {
		
		V missive = build(clazz);
		System.arraycopy(bytes, index, missive.bytes, 0, byteSizeRegistry[classMap.get(clazz)]);
		
		return missive;
	}

	/**
	 * 
	 * @param tags
	 */
	@SuppressWarnings("unchecked")
	protected static void install(ValueTag<?>[] tags) {

		if (tags == null) {
			tags = new ValueTag<?>[0];
		}

		/* Get current class object */
		final Class<?>[] trace = new ClassUtil.ClassTrace().getClassContext();
		final Class<? extends ValueMissive> current = 
				(Class<? extends ValueMissive>) trace[2];
		Class<?> superClazz = current.getSuperclass();

		/* Build set of new tags not members of superclass */
		final Set<ValueTag<?>> tagSet = new HashSet<ValueTag<?>>();

		for (final ValueTag<?> t : tags) {
			tagSet.add(t);
		}

		ValueTag<?>[] newTagArray = null;
		if(ValueMissive.class.isAssignableFrom(superClazz)
				&& !superClazz.equals(ValueMissive.class)) {
			
			/* Retrieve tag array of superclass */
			ValueTag<?>[] superclassTags = new ValueTag<?>[0];
			try {
				superclassTags = tagRegistry[classMap.get(superClazz)];
			} catch (final Exception e) {
				throw new MissiveException(e);
			}
			
			/* Remove all tags in superclass from subclass's tag set */
			for (final ValueTag<?> t : superclassTags) {
				if(tagSet.contains(t)) {
					tagSet.remove(t);
				}
			}
			
			/* Copy superclass tags into subclass then add on new tags */
			newTagArray = new ValueTag<?>[superclassTags.length + tagSet.size()];
			System.arraycopy(superclassTags, 0, newTagArray, 0, superclassTags.length);
			System.arraycopy(tagSet.toArray(new ValueTag<?>[0]), 0, 
					newTagArray, superclassTags.length + 1, tagSet.size());
			
		} else {
			newTagArray = tagSet.toArray(new ValueTag<?>[0]);
		}

		/* Update tag registry */
		final ValueTag<?>[][] newTagRegistry = new ValueTag<?>[classCount.get() + 1][];
		System.arraycopy(tagRegistry, 0, newTagRegistry, 0, classCount.get());
		newTagRegistry[classCount.get()] = newTagArray;
		tagRegistry = newTagRegistry;

		/* Build new index array and update index registry */
		final int[] newIndexes = new int[ValueTag.maxIndex()];
		for (int i = 0; i < ValueTag.maxIndex(); i++) {
			newIndexes[i] = EMPTY_ENTRY;
		}
		int counter = 0;
		for (final ValueTag<?> t : tagSet) {
			newIndexes[t.index()] = counter;
			counter += t.type().size();
		}
		
		/* Build new buffer size array and update buffer size registry */
		final int[] newBufferSizeRegistry = new int[classCount.get() + 1];
		System.arraycopy(byteSizeRegistry, 0, newBufferSizeRegistry, 0, classCount.get());
		int size = 0;
		for(final ValueTag<?> tag : tags) {
			size += tag.type().size();
		}
		newBufferSizeRegistry[classCount.get()] = size;
		byteSizeRegistry = newBufferSizeRegistry;

		final int[][] newIndexRegistry = new int[classCount.get() + 1][];
		System.arraycopy(indexRegistry, 0, newIndexRegistry, 0,
				classCount.get());
		newIndexRegistry[classCount.get()] = newIndexes;
		indexRegistry = newIndexRegistry;

		/* Assign new class code and update counter */
		classMap.put(current, classCount.getAndIncrement());

	}

	/*
	 * 
	 */
	protected static synchronized void incrementIndexRegistry() {

		final int oldSize = ValueTag.maxIndex();

		for (int i = 0; i < indexRegistry.length; i++) {
			final int[] newIndexes = new int[oldSize + 1];
			System.arraycopy(indexRegistry[i], 0, newIndexes, 0, oldSize);
			indexRegistry[i] = newIndexes;
			indexRegistry[i][oldSize] = EMPTY_ENTRY;
		}

	}
	
	/*
	 * 
	 */
	static <V extends ValueMissive> int bytesIn(final Class<V> clazz) {
		return byteSizeRegistry[classMap.get(clazz)];
	}
	
	/* ***** ***** Begin public methods ***** ***** */
	
	/**
	 * 
	 * @param tag
	 * @return
	 * @throws MissiveException
	 */
	public <V, T extends ValueType<V>> V get(final ValueTag<T> tag) throws MissiveException {
		return tag.type().getValue(bytes, indexRegistry[classCode][tag.index()]);
	}
	
	/**
	 * 
	 * @param tag
	 * @param value
	 * @throws MissiveException
	 */
	public<V, T extends ValueType<V>> void set(final ValueTag<T> tag, final V value)
			throws MissiveException {
		tag.type().putValue(value, bytes, indexRegistry[classCode][tag.index()]);
	}

	/**
	 * 
	 * @param tag
	 * @return
	 */
	public boolean contains(final ValueTag<?> tag) {
		return indexRegistry[classCode][tag.index()] != EMPTY_ENTRY;
	}

	/**
	 * 
	 * @return
	 */
	public ValueTag<?>[] tags() {
		return tagRegistry[classCode];
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return tagRegistry[classCode].length;
	}
	
	/**
	 * 
	 * 
	 * @param subClazz
	 * @return
	 */
	public <V extends ValueMissive> V cast(final Class<V> subClazz) {
		
		if(!subClazz.isAssignableFrom(getClass())) {
			throw new MissiveException(subClazz.getName() + " is not a subclass of " + 
						getClass().getName());
		}
		
		/* If the current byte array is shorter than the subclass', pad */
		if(bytes.length < byteSizeRegistry[classMap.get(subClazz)]) {
			
			byte[] subBytes = new byte[byteSizeRegistry[classMap.get(subClazz)]];
			System.arraycopy(bytes, 0, subBytes, 0, bytes.length);
			return build(subClazz, subBytes, 0);
			
		} else {
		
			/* Otherwise, pass current byte array to new subClass */
			return build(subClazz, bytes, 0);
			
		}
	}
	
	@Override
	public boolean equals(final Object o) {
		
		if(o == null) {
			return false;
		}
		
		if(this == o) {
			return true;
		}
		
		if(!(o instanceof ValueMissive)) {
			return false;
		}
		
		final ValueMissive m = (ValueMissive)o;
		
		return compareBytes(m.bytes);
		
	}
	
	private boolean compareBytes(final byte[] that) {
		
		if(bytes.length != that.length) {
			return false;
		}
		
		for(int i = 0; i < bytes.length; i++) {
			if(bytes[i] != that[i]) {
				return false;
			}
		}
		
		return true;
	}
	
}
