/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.hash;

import static com.barchart.missive.TestSpec.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.core.TagMapUnsafe;

public class TestHashUnsafeTagMap {

	@Test
	public void test() {
		
		final TagMapUnsafe map = new HashUnsafeTagMap(TAGS);
		
		/* Test put */
		map.put(BOOLEAN2, true);
		assertTrue(map.contains(BOOLEAN2));
		assertTrue(map.get(BOOLEAN2) == true);
		
		/* Test put raw */
		map.putRaw(BOOLEAN3, "true");
		assertTrue(map.contains(BOOLEAN3));
		assertTrue(map.get(BOOLEAN3) == true);
		
		/* Test remove */
		map.remove(BOOLEAN2);
		map.remove(BOOLEAN3);
		assertTrue(!map.contains(BOOLEAN2));
		assertTrue(!map.contains(BOOLEAN3));
		
	}
	
}
