/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import static com.barchart.missive.TestSpec.BOOLEAN1;
import static com.barchart.missive.TestSpec.BOOLEAN2;
import static com.barchart.missive.TestSpec.BOOLEAN3;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.ENUM;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.SHORT;
import static com.barchart.missive.TestSpec.STRING;
import static com.barchart.missive.TestSpec.TAGS;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.TestSpec.TestEnum;
import com.barchart.missive.core.TagMapUnsafe;

public class TestFastUnsafeTagMap {
	
	@Test
	public void test() {
		
		final TagMapUnsafe map = new FastUnsafeTagMap(TAGS);
		
		/* Check contains */
		assertTrue(map.contains(ENUM));
		assertTrue(map.contains(BYTE));
		assertTrue(map.contains(SHORT));
		assertTrue(map.contains(INTEGER));
		assertTrue(map.contains(LONG));
		assertTrue(map.contains(FLOAT));
		assertTrue(map.contains(DOUBLE));
		assertTrue(map.contains(BOOLEAN1));
		assertTrue(map.contains(CHARACTER));
		assertTrue(map.contains(STRING));
		
		/* Check set and get */
		map.set(ENUM, TestEnum.T1);
		assertTrue(map.get(ENUM) == TestEnum.T1);
		
		map.set(BYTE, (byte)0);
		assertTrue(map.get(BYTE) == 0);
		
		map.set(SHORT, (short)0);
		assertTrue(map.get(SHORT) == 0);
		
		map.set(INTEGER, 0);
		assertTrue(map.get(INTEGER) == 0);
		
		map.set(LONG, 0L);
		assertTrue(map.get(LONG) == 0);
		
		map.set(FLOAT, 0.0f);
		assertTrue(map.get(FLOAT) == 0.0f);
		
		map.set(DOUBLE, 0.0);
		assertTrue(map.get(DOUBLE) == 0.0);
		
		map.set(BOOLEAN1, true);
		assertTrue(map.get(BOOLEAN1));
		
		map.set(CHARACTER, 'x');
		assertTrue(map.get(CHARACTER) == 'x');
		
		map.set(STRING, "Barchart");
		assertTrue(map.get(STRING).equals("Barchart"));
		
		/* Test put */
		map.put(BOOLEAN2, true);
		assertTrue(map.contains(BOOLEAN2));
		assertTrue(map.get(BOOLEAN2) == true);
		assertTrue(map.size() == TAGS.length + 1);
		
		/* Test put raw */
		map.putRaw(BOOLEAN3, "true");
		assertTrue(map.contains(BOOLEAN3));
		assertTrue(map.get(BOOLEAN3) == true);
		assertTrue(map.size() == TAGS.length + 2);
		
		/* Test remove */
		map.remove(BOOLEAN2);
		map.remove(BOOLEAN3);
		assertTrue(!map.contains(BOOLEAN2));
		assertTrue(!map.contains(BOOLEAN3));
		assertTrue(map.size() == TAGS.length);
		
		/* Recheck previously set values */
		assertTrue(map.get(ENUM) == TestEnum.T1);
		assertTrue(map.get(BYTE) == 0);
		assertTrue(map.get(SHORT) == 0);
		assertTrue(map.get(INTEGER) == 0);
		assertTrue(map.get(LONG) == 0);
		assertTrue(map.get(FLOAT) == 0.0f);
		assertTrue(map.get(DOUBLE) == 0.0);
		assertTrue(map.get(BOOLEAN1));
		assertTrue(map.get(CHARACTER) == 'x');
		assertTrue(map.get(STRING).equals("Barchart"));
	}

}
