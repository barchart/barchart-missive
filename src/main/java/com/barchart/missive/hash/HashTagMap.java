/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.core.MissiveException;

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
	public Tag<?>[] tagsList() {
		return map.keySet().toArray(new Tag<?>[0]);
	}

	@Override
	public int mapSize() {
		return map.size();
	}
	
	//TODO Handle arrays and nesting
	@SuppressWarnings("rawtypes")
	@Override
	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
		
		for(final Entry<Tag, Object> e : map.entrySet()) {
			sb.append("\n").append(e.getKey().name()).append(":");
			if(e.getValue() == null) {
				sb.append("null");
			} else {
				sb.append(e.getValue().toString());
			}
		}
		
		
		return sb.toString();
			
	}

}
