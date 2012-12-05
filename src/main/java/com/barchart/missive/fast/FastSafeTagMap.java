package com.barchart.missive.fast;

import java.util.Arrays;

import com.barchart.missive.MissiveException;
import com.barchart.missive.Tag;
import com.barchart.missive.core.SafeTagMap;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class FastSafeTagMap implements SafeTagMap {
	
	protected volatile Tag<?>[] tags = new Tag<?>[0];
	protected volatile Tag<?>[] tagsByIndex = new Tag<?>[0];
	protected volatile int[] indexLookup = new int[0];
	
	protected volatile int maxTagCode = 0;
	
	protected Object[] values = new Object[0];
	
	protected FastSafeTagMap() {
		
	}
	
	public FastSafeTagMap(final Tag<?>[] tagz) {
		
		tags = tagz;
		
		for(final Tag<?> tag : tagz) {
			if(maxTagCode < tag.index()) {
				maxTagCode = tag.index();
			}
		}
		tagsByIndex = new Tag<?>[maxTagCode+1];
		values = new Object[tagz.length];
		indexLookup = new int[maxTagCode+1];
		
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
		
		if(maxTagCode == 0) {
			return false;
		}
		
		if(tag.index() > maxTagCode) {
			return false;
		}
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
		return tags.length;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void putRaw(final Tag newTag, final Object newValue) {
		put(newTag, newTag.cast(newValue));
	}
	
	protected <V> void put(final Tag<V> newTag, V newValue) {
		
		if(containsTag(newTag)) {
			set(newTag, newValue);
		} else {
			
			/* Increase array size */
			if(newTag.index() >= maxTagCode) {
				maxTagCode = newTag.index();
				
				Tag<?>[] tempTags = new Tag<?>[maxTagCode+1];
				System.arraycopy(tagsByIndex, 0, tempTags, 0, tagsByIndex.length);
				tagsByIndex = tempTags;
				
				int[] tempIndexes = new int[maxTagCode+1];
				System.arraycopy(indexLookup, 0, tempIndexes, 0, indexLookup.length);
				int tempIndex = indexLookup.length;
				indexLookup = tempIndexes;
				indexLookup[newTag.index()] = tempIndex;
				
				Object[] tempVals = new Object[values.length+1];
				System.arraycopy(values, 0, tempVals, 0, values.length);
				values = tempVals;
			}
			
			Tag<?>[] temp = new Tag<?>[tags.length + 1];
			System.arraycopy(tags, 0, temp, 0, tags.length);
			tags = temp;
			
			Object[] tempValues = new Object[tags.length];
			System.arraycopy(values, 0, tempValues, 0, values.length);
			values = tempValues;
			
			tagsByIndex[newTag.index()] = newTag;
			indexLookup[newTag.index()] = values.length-1;
			values[indexLookup[newTag.index()]] = newValue;
			
			tags[tags.length - 1] = newTag;
		}
		
	}
	
	protected static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	protected static int[] concat(int[] first, int[] second) {
		int[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
}
