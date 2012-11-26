package com.barchart.missive.core;

import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface SafeTagMap {

	public <V> V get(Tag<V> tag);
	
	public <V> void set(Tag<V> tag, V value);
	
	public boolean containsTag(Tag<?> tag);
	
	public Tag<?>[] getTags();
	
	public int size();
	
}
