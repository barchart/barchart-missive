package com.barchart.missive.fast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.barchart.missive.Tag;
import com.barchart.missive.core.Missive;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class FastMissive extends FastSafeTagMap implements Missive {
	
	protected static final Map<String, FastMissive> missives = new HashMap<String, FastMissive>();
	protected static final MissiveFactory factory = new MissiveFactory();
	
	protected FastMissive() {
		super();
		factory.put(this);
	}
	
	protected FastMissive(final Tag<?>[] tags) {
		super(tags);
		factory.put(this);
	}
	
	protected FastMissive(final Tag<?>[] newTagList, final Tag<?>[] newTags, final int newMaxTagCode) {
		tags = newTagList;
		tagsByIndex = newTags;
		maxTagCode = newMaxTagCode;
		values = new Object[maxTagCode + 1];
	}
	
	/**
	 * Utility method for building classes which extend this class
	 * 
	 * @param tagz
	 */
	protected void includeTags(final Tag<?>[] tagz) {
		
		int newMaxTagCode = 0;
		for(final Tag<?> tag : tagz) {
			if(newMaxTagCode < tag.index()) {
				newMaxTagCode = tag.index();
			}
		}
		
		if(newMaxTagCode > maxTagCode) {
			tagsByIndex = concat(tagsByIndex, new Tag<?>[newMaxTagCode - maxTagCode + 1]);
			indexLookup = concat(indexLookup, new int[newMaxTagCode - maxTagCode + 1]);
			maxTagCode = newMaxTagCode;
		}
		
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>(Arrays.asList(concat(tags, tagz)));
		tags = tagSet.toArray(new Tag<?>[0]);
		
		int oldLastValueIndex = values.length-1;
		final Object[] tempVals = new Object[values.length+1];
		System.arraycopy(values, 0, tempVals, 0, values.length);
		values = tempVals;
		
		for(final Tag<?> tag : tagz) {
			tagsByIndex[tag.index()] = tag;
			oldLastValueIndex++;
			indexLookup[tag.index()] = oldLastValueIndex; 
		}
		
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M castAsSubclass(final M m) {
		for(final Tag tag : m.getTags()) {
			m.set(tag, get(tag));
		}
		return m;
	}

	@Override
	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
		
		for(final Tag<?> tag : tags) {
			if(values[tag.index()] != null) {
				sb.append(tag.getName() + " : " + values[tag.index()].toString() + "\n");
			} else {
				sb.append(tag.getName() + " : NULL\n");
			}
		}
		
		return sb.toString();
		
	}
	
}
