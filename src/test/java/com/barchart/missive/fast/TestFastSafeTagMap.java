package com.barchart.missive.fast;

import static com.barchart.missive.TestSpec.BOOLEAN1;
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
import com.barchart.missive.core.SafeTagMap;

public class TestFastSafeTagMap {
	
	@Test
	public void test() {
		
		final SafeTagMap map = new FastSafeTagMap(TAGS);
		
		/* Check contains */
		assertTrue(map.containsTag(ENUM));
		assertTrue(map.containsTag(BYTE));
		assertTrue(map.containsTag(SHORT));
		assertTrue(map.containsTag(INTEGER));
		assertTrue(map.containsTag(LONG));
		assertTrue(map.containsTag(FLOAT));
		assertTrue(map.containsTag(DOUBLE));
		assertTrue(map.containsTag(BOOLEAN1));
		assertTrue(map.containsTag(CHARACTER));
		assertTrue(map.containsTag(STRING));
		
		/* Check size */
		assertTrue(map.size() == 10);
		
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
		
	}

}