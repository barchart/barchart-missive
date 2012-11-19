package com.barchart.missive.refactoring;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface VarTagMap extends TagMap {

	public <V> void set(Tag<V> tag, V value);
	
}
