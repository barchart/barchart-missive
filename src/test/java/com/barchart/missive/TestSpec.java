/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive;

import com.barchart.missive.core.Tag;

public interface TestSpec {
	
	enum TestEnum {
		T1, T2
	};

	Tag<TestEnum> ENUM = Tag.create("ENUM", TestEnum.class);
	Tag<Byte> BYTE = Tag.create("BYTE", Byte.class);
	Tag<Short> SHORT = Tag.create("SHORT", Short.class);
	Tag<Integer> INTEGER = Tag.create("INTEGER", Integer.class);
	Tag<Long> LONG = Tag.create("LONG", Long.class);
	Tag<Float> FLOAT = Tag.create("FLOAT", Float.class);
	Tag<Double> DOUBLE = Tag.create("DOUBLD", Double.class);
	Tag<Boolean> BOOLEAN1 = Tag.create("BOOLEAN1", Boolean.class);
	Tag<Boolean> BOOLEAN2 = Tag.create("BOOLEAN2", Boolean.class);
	Tag<Boolean> BOOLEAN3 = Tag.create("BOOLEAN3", Boolean.class);
	Tag<Character> CHARACTER = Tag.create("CHARACTER", Character.class);
	Tag<String> STRING = Tag.create("STRING", String.class);

	Tag<String> EXTEND = new Tag<String>("EXTEND", String.class);

	Tag<?>[] TAGS = new Tag<?>[] { ENUM, BYTE, SHORT, INTEGER, LONG, FLOAT,
			DOUBLE, BOOLEAN1, CHARACTER, STRING };

	Tag<?>[] TAGSEXT = new Tag<?>[] { ENUM, BYTE, SHORT, INTEGER, LONG, FLOAT,
			DOUBLE, BOOLEAN1, CHARACTER, STRING, EXTEND };

	public static TestEnum ENUM_V = TestEnum.T1;
	public static Byte BYTE_V = (byte)0;
	public static Short SHORT_V = (short)1;
	public static Integer INTEGER_V = (int)2;
	public static Long LONG_V = (long)3;
	public static Float FLOAT_V = (float)0.1;
	public static Double DOUBLE_V = (double)0.2;
	public static Boolean BOOLEAN1_V = true;
	public static Character CHARACTER_V = 'x';
	public static String STRING_V = "test";
	
}
