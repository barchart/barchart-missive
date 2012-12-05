package com.barchart.missive.hash;

import static com.barchart.missive.TestSpec.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.core.TagMap;

public class TestHashTagMap {

	@Test
	public void test() {
		
		final TagMap map = new HashTagMap(TAGS);
		
		/* Test put */
		map.put(BOOLEAN2, true);
		assertTrue(map.containsTag(BOOLEAN2));
		assertTrue(map.get(BOOLEAN2) == true);
		
		/* Test put raw */
		map.putRaw(BOOLEAN3, "true");
		assertTrue(map.containsTag(BOOLEAN3));
		assertTrue(map.get(BOOLEAN3) == true);
		
		/* Test remove */
		map.remove(BOOLEAN2);
		map.remove(BOOLEAN3);
		assertTrue(!map.containsTag(BOOLEAN2));
		assertTrue(!map.containsTag(BOOLEAN3));
		
	}
	
}
