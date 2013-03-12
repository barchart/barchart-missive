package com.barchart.missive.core;

import com.barchart.missive.api.Tag;

public class TestSpec {

	public enum TestEnum {
		T1, T2
	};

	public static final Tag<TestEnum> ENUM = TagFactory.create("ENUM", TestEnum.class);
	public static final Tag<Byte> BYTE = TagFactory.create("BYTE", Byte.class);
	public static final Tag<Short> SHORT = TagFactory.create("SHORT", Short.class);
	public static final Tag<Integer> INTEGER = TagFactory.create("INTEGER", Integer.class);
	public static final Tag<Long> LONG = TagFactory.create("LONG", Long.class);
	public static final Tag<Float> FLOAT = TagFactory.create("FLOAT", Float.class);
	public static final Tag<Double> DOUBLE = TagFactory.create("DOUBLD", Double.class);
	public static final Tag<Boolean> BOOLEAN1 = TagFactory.create("BOOLEAN1", Boolean.class);
	public static final Tag<Boolean> BOOLEAN2 = TagFactory.create("BOOLEAN2", Boolean.class);
	public static final Tag<Boolean> BOOLEAN3 = TagFactory.create("BOOLEAN3", Boolean.class);
	public static final Tag<Character> CHARACTER = TagFactory.create("CHARACTER", Character.class);
	public static final Tag<String> STRING = TagFactory.create("STRING", String.class);

	/* Test collections */
	public static final Tag<TestList> LIST = TagFactory.create("LIST", TestList.class);
	public static final Tag<TestSimpleMissive> MISSIVE = 
			TagFactory.create("MISSIVE", TestSimpleMissive.class);

	public static final Tag<?>[] SIMPLE_TAGS = new Tag<?>[] { ENUM, BYTE, SHORT, 
			INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN1, CHARACTER, STRING };

	public static final Tag<?>[] ALL_TAGS = TagFactory.collectTop(TestSpec.class);
	
	public static final TestEnum ENUM_V = TestEnum.T1;
	public static final Byte BYTE_V = (byte)0;
	public static final Short SHORT_V = (short)1;
	public static final Integer INTEGER_V = (int)2;
	public static final Long LONG_V = (long)3;
	public static final Float FLOAT_V = (float)0.1;
	public static final Double DOUBLE_V = (double)0.2;
	public static final Boolean BOOLEAN1_V = true;
	public static final Character CHARACTER_V = 'x';
	public static final String STRING_V = "test";
	
}
