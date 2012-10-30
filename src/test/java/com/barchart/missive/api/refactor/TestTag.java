package com.barchart.missive.api.refactor;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class TestTag {

	private enum TestEnum {
		T1, T2
	};
	
	//TODO Make into unit test
	public static void main(final String[] args) {
		
		Tag<TestEnum> TestEnum = new Tag<TestEnum>("TestEag", TestEnum.class);
		
		Tag<Byte> TestByte = new Tag<Byte>("TestByte", Byte.class);
		Tag<Short> TestShort = new Tag<Short>("TestShort", Short.class);
		Tag<Integer> TestInteger = new Tag<Integer>("TestInteger", Integer.class);
		Tag<Long> TestLong = new Tag<Long>("TestLong", Long.class);
		Tag<Float> TestFloat = new Tag<Float>("TestFloat", Float.class);
		Tag<Double> TestDouble = new Tag<Double>("TestDouble", Double.class);
		Tag<Boolean> TestBoolean = new Tag<Boolean>("TestBoolean", Boolean.class);
		Tag<Character> TestCharacter = new Tag<Character>("TestCharacter", Character.class);
		
		//Test casting from String
		System.out.println(TestEnum.cast("T1").name());
		
		System.out.println(TestByte.cast("1").toString());
		System.out.println(TestShort.cast("2").toString());
		System.out.println(TestInteger.cast("3").toString());
		System.out.println(TestLong.cast("4").toString());
		System.out.println(TestFloat.cast("1.1").toString());
		System.out.println(TestDouble.cast("2.2").toString());
		System.out.println(TestCharacter.cast("X").toString());
		
		System.out.println(TestBoolean.cast("true").toString());
		System.out.println(TestBoolean.cast("Y").toString());
		System.out.println(TestBoolean.cast("y").toString());
		System.out.println(TestBoolean.cast("True").toString());
		
	}
	
}
