package com.barchart.missive.lexicon;

import com.barchart.missive.NewLexicon;
import com.barchart.missive.Tag;

public class TestNewLexicon extends NewLexicon {

	public static final Tag<Byte> TestByte = new Tag<Byte>("TestByte", Byte.class);
	public static final Tag<Short> TestShort = new Tag<Short>("TestShort", Short.class);
	public static final Tag<Integer> TestInteger = new Tag<Integer>("TestInteger", Integer.class);
	public static final Tag<Long> TestLong = new Tag<Long>("TestLong", Long.class);
	public static final Tag<Float> TestFloat = new Tag<Float>("TestFloat", Float.class);
	public static final Tag<Double> TestDouble = new Tag<Double>("TestDouble", Double.class);
	public static final Tag<Boolean> TestBoolean = new Tag<Boolean>("TestBoolean", Boolean.class);
	public static final Tag<Character> TestCharacter = new Tag<Character>("TestCharacter", Character.class);
	
	static {
		build();
	}
	
	public static void main(final String[] args) {
		
		
		
	}
	
}
