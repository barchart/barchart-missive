package com.barchart.missive.hash;

import com.barchart.missive.Tag;
import com.barchart.missive.core.Missive;
import com.barchart.missive.core.RawMissive;

/**
 * A Java Collections hash map backed implementation.
 * 
 * @author Gavin M Litchfield
 *
 */
public class HashRawMissive extends HashTagMap implements RawMissive {

	public HashRawMissive(Tag<?>[] tags) {
		super(tags);
	}

	@Override
	public boolean isSupersetOf(final Missive m) {
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

}
