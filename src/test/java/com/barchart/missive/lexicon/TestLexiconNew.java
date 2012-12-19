/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.lexicon;

import com.barchart.missive.core.LexiconNew;
import com.barchart.missive.core.Tag;

public class TestLexiconNew extends LexiconNew {

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
