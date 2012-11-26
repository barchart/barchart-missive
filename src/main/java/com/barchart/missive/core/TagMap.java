package com.barchart.missive.core;

import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface TagMap extends SafeTagMap {
	
	@SuppressWarnings("rawtypes")
	void putRaw(Tag tag, Object value);
	
	<V> void put(Tag<V> tag, V v);
	
	@SuppressWarnings("rawtypes")
	Object remove(Tag tag);
	
	TagMap extend(final Tag<?>[] tags);
	
	SafeTagMap createInstance();
	
}
