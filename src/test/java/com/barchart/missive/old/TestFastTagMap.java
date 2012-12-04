package com.barchart.missive.old;

import com.barchart.missive.Tag;
import com.barchart.missive.core.SafeTagMap;
import com.barchart.missive.core.TagMap;
import com.barchart.missive.fast.FastTagMap;

public class TestFastTagMap {

	public static void main(final String[] args) {
		
		final int mapSize = 10;
		final Tag<?>[] tags = new Tag<?>[mapSize];

		for (int i = 0; i < mapSize; i++) {
			tags[i] = Tag.create(String.valueOf(i), String.class);
		}
		
		final Tag<?>[] newTags = new Tag<?>[mapSize];
		for(int i = 0; i < mapSize; i++) {
			newTags[i] = Tag.create(String.valueOf(i) + "NEW", String.class);
		}
		
		final FastTagMap base = new FastTagMap(tags);
		
		final SafeTagMap instance1 = base.createInstance();
		final SafeTagMap instance2 = base.createInstance();
		
		final TagMap extended = base.extend(newTags);

		for(final Tag<?> tag : extended.getTags()) {
			System.out.println(tag.getName());
		}
		
		base.putRaw(Tag.create("TESTXXX", String.class), "TEST VALUE");
		
		for(final Tag<?> tag : instance1.getTags()) {
			System.out.println(tag.getName());
		}
		
		for(final Tag<?> tag : instance2.getTags()) {
			System.out.println(tag.getName());
		}
		
		for(final Tag<?> tag : extended.getTags()) {
			System.out.println(tag.getName());
		}
		
	}
	
}
