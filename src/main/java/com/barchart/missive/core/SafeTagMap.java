package com.barchart.missive.core;

import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface SafeTagMap extends DefTagMap {

	public <V> void set(Tag<V> tag, V value);
	
}
