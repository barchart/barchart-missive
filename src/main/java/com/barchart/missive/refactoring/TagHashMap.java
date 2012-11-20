package com.barchart.missive.refactoring;

import com.barchart.algorithms.hashes.MPHF;
import com.barchart.missive.api.MissiveException;
import com.barchart.missive.api.Tag;

public class TagHashMap implements RawTagMap {
	
	private Tag<?>[] tags;
	private Object[] values;
	
	private MPHF hash;
	
	public TagHashMap(final Tag<?>[] tagz) {
		
		tags = new Tag<?>[tagz.length];
		values = new Object[tagz.length];
		
		final int[] hashCodes = new int[tagz.length];
		
		for(int i = 0; i < tagz.length; i++) {
			values[i] = null;
			hashCodes[i] = tagz[i].hashCode();
		}
		
		hash = new MPHF(hashCodes);
		
		for(final Tag<?> tag : tagz) {
			this.tags[hash.hash(tag.hashCode())] = tag;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) values[hash.hash(tag.hashCode())];
	}

	@Override
	public boolean containsTag(final Tag<?> tag) {
		return tags[hash.hash(tag.hashCode())] == tag;
	}

	@Override
	public Tag<?>[] getTags() {
		return tags;
	}

	@Override
	public int size() {
		return tags.length;
	}

	@Override
	public <V> void set(final Tag<V> tag, final V value) {
		if(containsTag(tag)) {
			values[hash.hash(tag.hashCode())] = value;
		} else {
			throw new MissiveException("Tag not in map : " + tag.getName());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void putRaw(final Tag newTag, final Object newValue) {
		if(containsTag(newTag)) {
			set(newTag, newTag.cast(newValue));
		} else {
			
			/* Build new tag array */
			final Tag<?>[] newTags = new Tag<?>[tags.length + 1]; 
			final Tag<?>[] newHashOrderTags = new Tag<?>[tags.length + 1];
			System.arraycopy(tags, 0, newTags, 0, tags.length);
			newTags[newTags.length - 1] = newTag;
			
			/* Build new hash function */
			final int[] newHashCodes = new int[newTags.length];
			final Object[] newValues = new Object[newTags.length];
			for(int i = 0; i < newTags.length; i++) {
				newHashCodes[i] = newTags[i].hashCode();
				newValues[i] = null;
			}
			final MPHF newHash = new MPHF(newHashCodes);

			/* Populate old tags and values with new hash function */
			for(final Tag tag : tags) {
				newHashOrderTags[newHash.hash(tag.hashCode())] = tag;
				newValues[newHash.hash(tag.hashCode())] = values[hash.hash(tag.hashCode())];
			}
			
			/* Add new tag and value */
			newHashOrderTags[newHash.hash(newTag.hashCode())] = newTag;
			newValues[newHash.hash(newTag.hashCode())] = newTag.cast(newValue);
			
			/* Replace old arrays */
			tags = newHashOrderTags;
			values = newValues;
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <V> V remove(final Tag oldTag) {
		
		if(!containsTag(oldTag)) {
			return null;
		}
		
		final V oldValue = (V) values[hash.hash(oldTag.hashCode())];
		
		/* Build new tag array */
		final Tag<?>[] newTags = new Tag<?>[tags.length - 1]; 
		final Tag<?>[] newHashOrderTags = new Tag<?>[tags.length - 1];
		final int oldTagPos = hash.hash(oldTag.hashCode());
		System.arraycopy(tags, 0, newTags, 0, oldTagPos - 1);
		System.arraycopy(tags, oldTagPos + 1, newTags, oldTagPos, tags.length - oldTagPos);
		
		/* Build new hash function */
		final int[] newHashCodes = new int[newTags.length];
		final Object[] newValues = new Object[newTags.length];
		for(int i = 0; i < newTags.length; i++) {
			newHashCodes[i] = newTags[i].hashCode();
			newValues[i] = null;
		}
		final MPHF newHash = new MPHF(newHashCodes);
		
		/* Populate old tags and values with new hash function */
		for(final Tag tag : newTags) {
			newHashOrderTags[newHash.hash(tag.hashCode())] = tag;
			newValues[newHash.hash(tag.hashCode())] = values[hash.hash(tag.hashCode())];
		}
		
		/* Replace old arrays */
		tags = newHashOrderTags;
		values = newValues;
		
		return oldValue;
	}
	
}
