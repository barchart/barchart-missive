/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;


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
	
	public boolean isSupersetOf(SafeTagMap map);
	
}
