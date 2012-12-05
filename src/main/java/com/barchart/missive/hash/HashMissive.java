package com.barchart.missive.hash;

import com.barchart.missive.Tag;
import com.barchart.missive.core.Missive;

/**
 * A Java Collections hash map backed implementation.
 * 
 * @author Gavin M Litchfield
 *
 */
public class HashMissive extends HashSafeTagMap implements Missive {

	public HashMissive(Tag<?>[] tags) {
		super(tags);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M castAsSubclass(final M m) {
		
		for(final Tag tag : m.getTags()) {
			m.set(tag, get(tag));
		}
		
		return m;
	}

}