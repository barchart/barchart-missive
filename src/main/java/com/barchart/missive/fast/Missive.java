package com.barchart.missive.fast;

import java.util.Arrays;

import com.barchart.missive.MissiveException;
import com.barchart.missive.Tag;
import com.barchart.missive.core.TagMap;

public class Missive {
	
	protected volatile Tag<?>[] visibleTags = new Tag<?>[0];
	protected volatile Tag<?>[] visTagsByIndex = new Tag<?>[0];
	
	protected volatile int maxTagCode = 0;
	
	protected TagMap map = new FastTagMap();
	
	protected Missive() {
		
	}
	
	/**
	 * Constructor to be used by subclasses
	 * 
	 * @param tags
	 */
	protected Missive(final Tag<?>[] tags) {
		
		visibleTags = tags; // Make copy?
		
		for(final Tag<?> tag : tags) {
			if(maxTagCode < tag.index()) {
				maxTagCode = tag.index();
			}
		}
		
		visTagsByIndex = new Tag<?>[maxTagCode+1];
		
		for(final Tag<?> tag : tags) {
			visTagsByIndex[tag.index()] = tag;
		}
		
		MissiveFactory.put(this);
		
	}
	
	/**
	 * Constructor used by MissiveFactory
	 * 
	 * @param visibleTags
	 * @param visTagsByIndex
	 * @param maxTagCode
	 * @param map
	 */
	Missive(final Tag<?>[] visibleTags, final Tag<?>[] visTagsByIndex, 
			final int maxTagCode, final TagMap map) {
		this.visibleTags = visibleTags;
		this.visTagsByIndex = visTagsByIndex;
		this.maxTagCode = maxTagCode;
		this.map = map;
	}
	
	/**
	 * Utility method for building classes which extend this class
	 * 
	 * @param tags
	 */
	protected void include(final Tag<?>[] tags) {
		
		int newMaxTagCode = 0;
		for(final Tag<?> tag : tags) {
			if(newMaxTagCode < tag.index()) {
				newMaxTagCode = tag.index();
			}
		}
		
		if(newMaxTagCode > maxTagCode) {
			visTagsByIndex = concat(visTagsByIndex, new Tag<?>[newMaxTagCode - maxTagCode + 1]);
			maxTagCode = newMaxTagCode;
		}
		
		for(final Tag<?> tag : tags) {
			visTagsByIndex[tag.index()] = tag;
		}
		
		visibleTags = concat(visibleTags, tags);
	}
	
	public <V> V get(final Tag<V> tag) {
		
		if(tag.index() > maxTagCode) {
			throw new MissiveException("Tag not visible in missive");
		}
		
		if(visTagsByIndex[tag.index()] == null) {
			throw new MissiveException("Tag not visible in missive");
		}
		
		return map.get(tag);
	}
	
	public <M extends Missive> M castAsSubclass(final M m) {
		
		// check if good to cast
		m.map = map;
		
		return m;
	}
	
	public Tag<?>[] getViaibleTags() {
		return visibleTags;  // Make Copy
	}
	
	public int size() {
		return visibleTags.length;
	}
	
	protected static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
}
