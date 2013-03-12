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
 * 
 * @author Gavin M Litchfield
 * 
 * @param <V>
 *            The class type of the value this tag represents.
 * 
 */
public interface Tag<V> extends Comparable<Tag<?>>{

	public V cast(Object o) throws MissiveException;

	/** Tag value type. */
	public Class<V> classType();

	/** Tag name. */
	public String name();

	/** Tag instantiation index. */
	public int index();

	/**
	 * @return true if parameterized type is an enum
	 */
	public boolean isEnum();

	/**
	 * @return true if parameterized type is an array or subclass of a 
	 * 			Java collection
	 */
	public boolean isList();

	/**
	 * @return true if parameterized type is a Java primitive
	 */
	public boolean isPrim();

}
