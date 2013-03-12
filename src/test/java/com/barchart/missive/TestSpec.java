/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;

public interface TestSpec {
	
	enum TestEnum {
		T1, T2
	};

	Tag<TestEnum> ENUM = TagFactory.create("ENUM", TestEnum.class);
	Tag<Byte> BYTE = TagFactory.create("BYTE", Byte.class);
	Tag<Short> SHORT = TagFactory.create("SHORT", Short.class);
	Tag<Integer> INTEGER = TagFactory.create("INTEGER", Integer.class);
	Tag<Long> LONG = TagFactory.create("LONG", Long.class);
	Tag<Float> FLOAT = TagFactory.create("FLOAT", Float.class);
	Tag<Double> DOUBLE = TagFactory.create("DOUBLD", Double.class);
	Tag<Boolean> BOOLEAN1 = TagFactory.create("BOOLEAN1", Boolean.class);
	Tag<Boolean> BOOLEAN2 = TagFactory.create("BOOLEAN2", Boolean.class);
	Tag<Boolean> BOOLEAN3 = TagFactory.create("BOOLEAN3", Boolean.class);
	Tag<Character> CHARACTER = TagFactory.create("CHARACTER", Character.class);
	Tag<String> STRING = TagFactory.create("STRING", String.class);

	Tag<String> EXTEND = TagFactory.create("EXTEND", String.class);

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
