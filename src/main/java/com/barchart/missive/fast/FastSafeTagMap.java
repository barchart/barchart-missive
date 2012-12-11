package com.barchart.missive.fast;

import java.util.Arrays;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.SafeTagMap;
import com.barchart.missive.core.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class FastSafeTagMap implements SafeTagMap {
	
	private static final int DEFAULT_SIZE = 30;
	
	protected volatile Tag<?>[] tags;
	protected Object[] values;
	
	protected volatile Tag<?>[] tagsByIndex = new Tag<?>[Tag.maxIndex()];
	protected volatile int[] indexLookup = new int[Tag.maxIndex()];
	
	protected volatile int maxTagCode = 0;
	protected volatile int size = 0;
 	
	protected FastSafeTagMap() {
		tags = new Tag<?>[DEFAULT_SIZE];
		values = new Object[DEFAULT_SIZE];
	}
	
	public FastSafeTagMap(final int initialSize) {
		tags = new Tag<?>[initialSize];
		values = new Object[initialSize];
	}
	
	public FastSafeTagMap(final Tag<?>[] tagz) {
		
		tags = tagz;
		size = tagz.length;
		
		values = new Object[tagz.length];
		
		int counter = 0;
		for(final Tag<?> tag : tagz) {
			tagsByIndex[tag.index()] = tag;
			indexLookup[tag.index()] = counter;
			counter++;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) values[indexLookup[tag.index()]];
	}
	
	@Override
	public <V> void set(final Tag<V> tag, final V value) {
		if(containsTag(tag)) {
			values[indexLookup[tag.index()]] = value;
		} else {
			throw new MissiveException("Tag not in map : " + tag.getName());
		}
	}
	
	@Override
	public boolean containsTag(final Tag<?> tag) {
		return tagsByIndex[tag.index()] != null;
	}

	@Override
	public Tag<?>[] getTags() {
		final Tag<?>[] copy = new Tag<?>[tags.length];
		System.arraycopy(tags, 0, copy, 0, tags.length);
		return copy;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	protected static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
}
