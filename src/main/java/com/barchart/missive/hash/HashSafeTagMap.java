/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.SafeTagMap;
import com.barchart.missive.core.Tag;

/**
 * A Java Collections hash map backed implementation.
 * 
 * @author Gavin M Litchfield
 *
 */
public class HashSafeTagMap implements SafeTagMap  {
	
	protected final Map<Tag<?>, Object> map = new HashMap<Tag<?>, Object>();
	
	public HashSafeTagMap(final Tag<?>[] tags) {
		for(final Tag<?> tag : tags) {
			map.put(tag, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) map.get(tag);
	}

	@Override
	public <V> void set(final Tag<V> tag, final V value) {
		if(map.containsKey(tag)) {
			map.put(tag, value);
		} else {
			throw new MissiveException("Tag not in map : " + tag.getName());
		}
		
	}

	@Override
	public boolean containsTag(final Tag<?> tag) {
		return map.containsKey(tag);
	}

	@Override
	public Tag<?>[] getTags() {
		return map.keySet().toArray(new Tag<?>[0]);
	}

	@Override
	public int size() {
		return map.size();
	}
	
	protected static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}

}
