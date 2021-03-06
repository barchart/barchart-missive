/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.api;

import com.barchart.missive.core.MissiveException;

/**
 * mutable, type safe
 * 
 * @author Gavin M Litchfield
 */
public interface TagMapSafe extends TagMap {

	<V> void set(Tag<V> tag, V value) throws MissiveException;

}
