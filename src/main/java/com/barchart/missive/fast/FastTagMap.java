package com.barchart.missive.fast;

import com.barchart.missive.Tag;
import com.barchart.missive.core.SafeTagMap;
import com.barchart.missive.core.TagMap;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class FastTagMap extends FastSafeTagMap implements TagMap {

	protected FastTagMap() {
		
	}
	
	public FastTagMap(final Tag<?>[] tagz) {
		super(tagz);
	}
	
	@Override
	public SafeTagMap createInstance() {
		
		final FastSafeTagMap temp = new FastSafeTagMap();
		temp.tagList = tagList;
		temp.tags = tags;
		temp.maxTagCode = maxTagCode;
		temp.values = new Object[maxTagCode+1];
		for(int i = 0; i < maxTagCode+1; i++) {
			temp.values[i] = null;
		}
		
		return temp;
	}
	
	@Override
	public TagMap extend(final Tag<?>[] tags) {
		return new FastTagMap(concat(tags, tagList));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) values[tag.hashCode()];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void putRaw(final Tag newTag, final Object newValue) {
		if(containsTag(newTag)) {
			set(newTag, newTag.cast(newValue));
		} else {
			
			/* Increase array size */
			if(newTag.hashCode() >= maxTagCode) {
				maxTagCode = newTag.hashCode();
				
				Tag<?>[] tempTags = new Tag<?>[maxTagCode+1];
				System.arraycopy(tags, 0, tempTags, 0, tags.length);
				tags = tempTags;
				
				Object[] tempVals = new Object[maxTagCode+1];
				System.arraycopy(values, 0, tempVals, 0, values.length);
				values = tempVals;
			}
			
			tags[newTag.hashCode()] = newTag;
			values[newTag.hashCode()] = newTag.cast(newValue);
			
			Tag<?>[] temp = new Tag<?>[tagList.length + 1];
			System.arraycopy(tagList, 0, temp, 0, tagList.length);
			tagList = temp;
			
			tagList[tagList.length - 1] = newTag;
		}
	}
	
	@Override
	public <V> void put(final Tag<V> newTag, V newValue) {
		
		if(containsTag(newTag)) {
			set(newTag, newValue);
		} else {
			
			/* Increase array size */
			if(newTag.hashCode() >= maxTagCode) {
				maxTagCode = newTag.hashCode();
				
				Tag<?>[] tempTags = new Tag<?>[maxTagCode+1];
				System.arraycopy(tags, 0, tempTags, 0, tags.length);
				tags = tempTags;
				
				Object[] tempVals = new Object[maxTagCode+1];
				System.arraycopy(values, 0, tempVals, 0, values.length);
				values = tempVals;
			}
			
			tags[newTag.hashCode()] = newTag;
			values[newTag.hashCode()] = newValue;
			
			Tag<?>[] temp = new Tag<?>[tagList.length + 1];
			System.arraycopy(tagList, 0, temp, 0, tagList.length);
			tagList = temp;
			
			tagList[tagList.length - 1] = newTag;
		}
		
	}
	
	@Override
	@SuppressWarnings({ "rawtypes"})
	public Object remove(final Tag oldTag) {
		
		if(!containsTag(oldTag)) {
			return null;
		}
		
		final Object oldValue = values[oldTag.hashCode()];
		
		tags[oldTag.hashCode()] = null;
		values[oldTag.hashCode()] = null;
		//TODO Remove tag from tagList
		
		return oldValue;
	}
	
}
