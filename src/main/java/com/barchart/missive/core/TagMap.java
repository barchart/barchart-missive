package com.barchart.missive.core;

import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface TagMap extends SafeTagMap {
	
	@SuppressWarnings("rawtypes")
	public void putRaw(Tag tag, Object value);
	
	public <V> void put(Tag<V> tag, V v);
	
	@SuppressWarnings("rawtypes")
	public Object remove(Tag tag);
	
}
