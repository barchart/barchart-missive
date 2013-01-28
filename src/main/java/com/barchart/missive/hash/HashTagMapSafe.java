package com.barchart.missive.hash;

import java.util.Map;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMapSafe;

public class HashTagMapSafe extends HashTagMap implements TagMapSafe {

	@SuppressWarnings("rawtypes")
	public HashTagMapSafe(final Map<Tag, Object> tmap) {
		super(tmap);
	}
	
	public HashTagMapSafe(final Tag<?>[] tags) {
		super(tags);
	}

	@Override
	public <V> void set(final Tag<V> tag, V value) throws MissiveException {
		if(map.containsKey(tag)) {
			map.put(tag, value);
		} else {
			throw new MissiveException("Tag not present in map");
		}
	}

}
