/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive;

import static com.barchart.missive.TestSpec.BOOLEAN1;
import static com.barchart.missive.TestSpec.BOOLEAN1_V;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.BYTE_V;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.CHARACTER_V;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.DOUBLE_V;
import static com.barchart.missive.TestSpec.ENUM;
import static com.barchart.missive.TestSpec.ENUM_V;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.FLOAT_V;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.INTEGER_V;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.LONG_V;
import static com.barchart.missive.TestSpec.SHORT;
import static com.barchart.missive.TestSpec.SHORT_V;
import static com.barchart.missive.TestSpec.STRING;
import static com.barchart.missive.TestSpec.STRING_V;
import static com.barchart.missive.TestSpec.TAGS;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.TestSpec.TestEnum;
import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;
import com.barchart.missive.api.TagMapSafe;

public final class ImplementationTests {
	
	@SuppressWarnings("rawtypes")
	@Test
	public static void testTagMap(final TagMap map) {
		
		/* Test contains */
		for(Tag t : TestSpec.TAGS) {
			assertTrue(map.contains(t));
		}
		
		/* Test get */
		assertTrue(map.get(ENUM) == ENUM_V);
		assertTrue(map.get(BYTE) == BYTE_V);
		assertTrue(map.get(SHORT) == SHORT_V);
		assertTrue(map.get(INTEGER) == INTEGER_V);
		assertTrue(map.get(LONG) == LONG_V);
		assertTrue(map.get(FLOAT).equals(FLOAT_V));
		assertTrue(map.get(DOUBLE).equals(DOUBLE_V));
		assertTrue(map.get(BOOLEAN1) == BOOLEAN1_V);
		assertTrue(map.get(CHARACTER) == CHARACTER_V);
		assertTrue(map.get(STRING).equals(STRING_V));

		assertTrue(map.mapSize() == TAGS.length);
		
	}
	
	@Test 
	public static void testTagMapSafe(final TagMapSafe map) {
		
		testTagMap(map);
		
		map.set(ENUM, TestEnum.T2);
		assertTrue(map.get(ENUM) == TestEnum.T2);
		
		map.set(BYTE, (byte)10);
		assertTrue(map.get(BYTE) == (byte)10);
		
		map.set(SHORT, (short)11);
		assertTrue(map.get(SHORT) == (short)11);
		
		map.set(INTEGER, (int)12);
		assertTrue(map.get(INTEGER) == (int)12);
		
		map.set(LONG, (long)13);
		assertTrue(map.get(LONG) == (long)13);
		
		map.set(FLOAT, (float)10.1);
		assertTrue(map.get(FLOAT).equals((float)10.1));
		
		map.set(DOUBLE, (double)11.1);
		assertTrue(map.get(DOUBLE).equals((double)11.1));
		
		map.set(BOOLEAN1, false);
		assertTrue(!map.get(BOOLEAN1));
		
		map.set(CHARACTER, 'c');
		assertTrue(map.get(CHARACTER) == 'c');
		
		map.set(STRING, "notTest");
		assertTrue(map.get(STRING).equals("notTest"));
		
	}
	
}
