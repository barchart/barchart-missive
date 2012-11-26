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
	
	protected Tag<?>[] tagList = new Tag<?>[0];
	protected Tag<?>[] tags = new Tag<?>[0];
	protected Object[] values = new Object[0];
	
	protected int maxTagCode = 0;
	
	protected FastSafeTagMap() {
		
	}
	
	public FastSafeTagMap(final Tag<?>[] tagz) {
		
		tagList = tagz;
		
		for(final Tag<?> tag : tagz) {
			if(maxTagCode < tag.hashCode()) {
				maxTagCode = tag.hashCode();
			}
		}
		tags = new Tag<?>[maxTagCode+1];
		values = new Object[maxTagCode+1];
		
		for(int i = 0; i < maxTagCode+1; i++) {
			values[i] = null;
			tags[i] = null;
		}
		
		for(final Tag<?> tag : tagz) {
			tags[tag.hashCode()] = tag;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) values[tag.hashCode()];
	}
	
	@Override
	public <V> void set(final Tag<V> tag, final V value) {
		if(containsTag(tag)) {
			values[tag.hashCode()] = value;
		} else {
			throw new MissiveException("Tag not in map : " + tag.getName());
		}
	}
	
	@Override
	public boolean containsTag(final Tag<?> tag) {
		//TODO need to add book keeping for first and last tag (???)
		
		if(maxTagCode == 0) {
			return false;
		}
		
		if(tag.hashCode() > maxTagCode) {
			return false;
		}
		return tags[tag.hashCode()] != null;
	}

	@Override
	public Tag<?>[] getTags() {
		final Tag<?>[] copy = new Tag<?>[tagList.length];
		System.arraycopy(tagList, 0, copy, 0, tagList.length);
		return copy;
	}
	
	@Override
	public int size() {
		return tagList.length;
	}
	
	protected static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
}
