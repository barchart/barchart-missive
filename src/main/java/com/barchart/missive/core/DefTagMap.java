package com.barchart.missive.core;


/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface DefTagMap {
	
	public <V> V get(Tag<V> tag);

	public boolean containsTag(Tag<?> tag);
	
	public Tag<?>[] getTags();
	
	public int size();
	
}
