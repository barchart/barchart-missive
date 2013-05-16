/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.api;

/**
 * mutable, not type safe
 * 
 * @author Gavin M Litchfield
 */
public interface TagMapUnsafe extends TagMapSafe {

	@SuppressWarnings("rawtypes")
	void putRaw(Tag tag, Object value);

	<V> void put(Tag<V> tag, V v);

	@SuppressWarnings("rawtypes")
	Object remove(Tag tag);

	boolean isSupersetOf(TagMapSafe map);

}
