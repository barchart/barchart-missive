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
public interface SafeTagMap extends DefTagMap {

	public <V> void set(Tag<V> tag, V value);
	
}
