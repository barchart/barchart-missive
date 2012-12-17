/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive;

import com.barchart.missive.core.Tag;

public final class TestSpec {
	
	public enum TestEnum {
		T1, T2
	};
	
	public static final Tag<TestEnum> ENUM = Tag.create("ENUM", TestEnum.class);
	public static final Tag<Byte> BYTE = Tag.create("BYTE", Byte.class);
	public static final Tag<Short> SHORT = Tag.create("SHORT", Short.class);
	public static final Tag<Integer> INTEGER = Tag.create("INTEGER", Integer.class);
	public static final Tag<Long> LONG = Tag.create("LONG", Long.class);
	public static final Tag<Float> FLOAT = Tag.create("FLOAT", Float.class);
	public static final Tag<Double> DOUBLE = Tag.create("DOUBLD", Double.class);
	public static final Tag<Boolean> BOOLEAN1 = Tag.create("BOOLEAN1", Boolean.class);
	public static final Tag<Boolean> BOOLEAN2 = Tag.create("BOOLEAN2", Boolean.class);
	public static final Tag<Boolean> BOOLEAN3 = Tag.create("BOOLEAN3", Boolean.class);
	public static final Tag<Character> CHARACTER = Tag.create("CHARACTER", Character.class);
	public static final Tag<String> STRING = Tag.create("STRING", String.class);
	
	public static final Tag<String> EXTEND = new Tag<String>("EXTEND", String.class);
	
	public static final Tag<?>[] TAGS = new Tag<?>[]{
		ENUM, BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN1, CHARACTER, STRING
	};
	
	public static final Tag<?>[] TAGSEXT = new Tag<?>[]{
		ENUM, BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN1, CHARACTER, STRING, EXTEND
	};
	
}
