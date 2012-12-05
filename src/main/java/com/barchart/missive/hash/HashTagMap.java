package com.barchart.missive.hash;

import com.barchart.missive.Tag;
import com.barchart.missive.core.TagMap;

/**
 * A Java Collections hash map backed implementation.
 * 
 * @author Gavin M Litchfield
 *
 */
public class HashTagMap extends HashSafeTagMap implements TagMap {

	public HashTagMap(Tag<?>[] tags) {
		super(tags);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public
	void putRaw(final Tag tag, final Object value) {
		map.put(tag, tag.cast(value));
	}

	@Override
	public <V> void put(final Tag<V> tag, final V v) {
		map.put(tag, v);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object remove(final Tag tag) {
		return map.remove(tag);
	}

}
