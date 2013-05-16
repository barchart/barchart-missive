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
 * @author Gavin M Litchfield
 * 
 * @param <V>
 *            The class type of the value this tag represents.
 */
public interface Tag<V> extends Comparable<Tag<?>> {

	V cast(Object o) throws MissiveException;

	/** Tag value type. */
	Class<V> classType();

	/** Tag name. */
	String name();

	/** Tag instantiation index. */
	int index();

	/**
	 * @return true if parameterized type is an enum
	 */
	boolean isEnum();

	/**
	 * @return true if parameterized type is an array or subclass of a Java
	 *         collection
	 */
	boolean isList();

	/**
	 * @return true if parameterized type is a Java primitive
	 */
	boolean isPrim();

	/**
	 * @return true is the parameterized type is a subclass of TagMap
	 */
	boolean isComplex();

}
