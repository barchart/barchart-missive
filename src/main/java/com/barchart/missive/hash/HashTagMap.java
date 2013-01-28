package com.barchart.missive.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMap;

public class HashTagMap implements TagMap {

	@SuppressWarnings("rawtypes")
	protected final Map<Tag, Object> map = new HashMap<Tag, Object>();
	
	@SuppressWarnings("rawtypes")
	public HashTagMap(final Map<Tag, Object> tmap) {
		
		for(final Entry<Tag, Object> e : tmap.entrySet()) {
			map.put(e.getKey(), e.getKey().cast(e.getValue()));
		}
		
	}
	
	/**
	 * Creates a map with null values.
	 * @param tags
	 */
	protected HashTagMap(final Tag<?>[] tags) {
		for(Tag<?> t : tags) {
			map.put(t, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(Tag<V> tag) throws MissiveException {
		return (V) map.get(tag);
	}

	@Override
	public boolean contains(Tag<?> tag) {
		return map.containsKey(tag);
	}

	@Override
	public Tag<?>[] tags() {
		return map.keySet().toArray(new Tag<?>[0]);
	}

	@Override
	public int size() {
		return map.size();
	}

}
