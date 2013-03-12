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
 * immutable
 * 
 * @author Gavin M Litchfield
 * 
 */
public interface TagMap {

	public <V> V get(Tag<V> tag) throws MissiveException;

	public boolean contains(Tag<?> tag);

	public Tag<?>[] tags();

	public int size();

}
