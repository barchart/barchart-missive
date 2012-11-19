package com.barchart.missive.refactoring;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface TagMap {

	public <V> V get(Tag<V> tag);
	
	public boolean containsTag(Tag<?> tag);
	
	public Tag<?>[] getTags();
	
	public int size();
	
}
