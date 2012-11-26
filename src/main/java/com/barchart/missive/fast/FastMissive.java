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
		tagList = newTagList;
		tags = newTags;
		maxTagCode = newMaxTagCode;
		values = new Object[maxTagCode + 1];
	}
	
	protected void includeTags(final Tag<?>[] tagz) {
		
		int newMaxTagCode = 0;
		for(final Tag<?> tag : tagz) {
			if(newMaxTagCode < tag.hashCode()) {
				newMaxTagCode = tag.hashCode();
			}
		}
		
		if(newMaxTagCode > maxTagCode) {
			tags = concat(tags, new Tag<?>[newMaxTagCode - maxTagCode + 1]);
			values = concat(values, new Object[newMaxTagCode - maxTagCode + 1]);
			maxTagCode = newMaxTagCode;
		}
		
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>(Arrays.asList(concat(tagList, tagz)));
		tagList = tagSet.toArray(new Tag<?>[0]);
		
		for(final Tag<?> tag : tagz) {
			tags[tag.hashCode()] = tag;
			values[tag.hashCode()] = null;
		}
		
	}
	
	protected <V> void put(final Tag<V> newTag, final V newValue) {
		
		if(containsTag(newTag)) {
			set(newTag, newTag.cast(newValue));
		} else {
			
			/* Increase array size */
			if(newTag.hashCode() > maxTagCode) {
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
	public boolean isSubsetOf(final Missive m) {
		for(final Tag<?> tag : m.getTags()) {
			if(!containsTag(tag)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M cast(final M m) {
		for(final Tag tag : m.getTags()) {
			m.set(tag, get(tag));
		}
		return m;
	}

	@Override
	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
		
		for(final Tag<?> tag : tagList) {
			if(values[tag.hashCode()] != null) {
				sb.append(tag.getName() + " : " + values[tag.hashCode()].toString() + "\n");
			} else {
				sb.append(tag.getName() + " : NULL\n");
			}
		}
		
		return sb.toString();
		
	}
	
}
