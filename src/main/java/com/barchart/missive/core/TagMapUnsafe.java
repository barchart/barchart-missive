/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

/**
 * 
 * mutable, not type safe
 * 
 * @author Gavin M Litchfield
 * 
 */
public interface TagMapUnsafe extends TagMapSafe {

	@SuppressWarnings("rawtypes")
	public void putRaw(Tag tag, Object value);

	public <V> void put(Tag<V> tag, V v);

	@SuppressWarnings("rawtypes")
	public Object remove(Tag tag);

	public boolean isSupersetOf(TagMapSafe map);

}
