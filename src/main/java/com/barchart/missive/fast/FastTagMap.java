package com.barchart.missive.fast;

import com.barchart.missive.Tag;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void putRaw(final Tag newTag, final Object newValue) {
		if(containsTag(newTag)) {
			set(newTag, newTag.cast(newValue));
		} else {
			
			/* Increase array size */
			if(newTag.index() >= maxTagCode) {
				
				maxTagCode = newTag.index();
				
				Tag<?>[] tempTags = new Tag<?>[maxTagCode+1];
				System.arraycopy(tags, 0, tempTags, 0, tags.length);
				tags = tempTags;
				
				int[] tempIndexes = new int[maxTagCode+1];
				System.arraycopy(indexLookup, 0, tempIndexes, 0, indexLookup.length);
				int tempIndex = indexLookup.length;
				indexLookup = tempIndexes;
				indexLookup[newTag.index()] = tempIndex;
				
				Object[] tempVals = new Object[values.length+1];
				System.arraycopy(values, 0, tempVals, 0, values.length);
				values = tempVals;
				
			}
			
			tags[newTag.index()] = newTag;
			values[indexLookup[newTag.index()]] = newTag.cast(newValue);
			
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
			if(newTag.index() >= maxTagCode) {
				maxTagCode = newTag.index();
				
				Tag<?>[] tempTags = new Tag<?>[maxTagCode+1];
				System.arraycopy(tags, 0, tempTags, 0, tags.length);
				tags = tempTags;
				
				int[] tempIndexes = new int[maxTagCode+1];
				System.arraycopy(indexLookup, 0, tempIndexes, 0, indexLookup.length);
				int tempIndex = indexLookup.length;
				indexLookup = tempIndexes;
				indexLookup[newTag.index()] = tempIndex;
				
				Object[] tempVals = new Object[values.length+1];
				System.arraycopy(values, 0, tempVals, 0, values.length);
				values = tempVals;
			}
			
			tags[newTag.index()] = newTag;
			values[indexLookup[newTag.index()]] = newValue;
			
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
		
		final Object oldValue = values[oldTag.index()];
		
		tags[oldTag.index()] = null;
		values[oldTag.index()] = null;
		//TODO Remove tag from tagList
		
		return oldValue;
	}
	
}
