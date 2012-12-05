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
	
	@SuppressWarnings("rawtypes")
	@Override
	public void putRaw(final Tag newTag, final Object newValue) {
		super.putRaw(newTag, newValue);
	}
	
	@Override
	public <V> void put(final Tag<V> newTag, V newValue) {
		super.put(newTag, newValue);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes"})
	public Object remove(final Tag oldTag) {
		
		if(!containsTag(oldTag)) {
			return null;
		}
		
		final Object oldValue = values[indexLookup[oldTag.index()]];
		
		int indexOfOldTag = 0;
		for(int i = 0; i < tags.length; i++) {
			if(tags[i] == oldTag) {
				indexOfOldTag = i;
				break;
			}
		}
		
		final Tag<?>[] newTagList = new Tag<?>[tags.length-1];
		System.arraycopy(tags, 0, newTagList, 0, indexOfOldTag);
		System.arraycopy(tags, indexOfOldTag+1, newTagList, indexOfOldTag, tags.length - indexOfOldTag - 1);

		final int indexOfOldValue = indexLookup[oldTag.index()];
		final Object[] newValues = new Object[tags.length-1];
		System.arraycopy(values, 0, newValues, 0, indexOfOldValue);
		System.arraycopy(values, indexOfOldValue+1, newValues, indexOfOldValue, values.length - indexOfOldValue - 1);
		
		/* Update the indexLookup array */
		for(int i = indexOfOldValue; i < values.length; i++) {
			indexLookup[tags[i].index()]--;
		}
		
		tagsByIndex[oldTag.index()] = null;
		
		tags = newTagList;
		values = newValues;
		
		//TODO investigate shortening tags and indexLookup if needed
		
		return oldValue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
