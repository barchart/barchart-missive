package com.barchart.missive.api;

import static com.barchart.missive.api.spec.TestSpec.BOOLEAN1;
import static com.barchart.missive.api.spec.TestSpec.BOOLEAN2;
import static com.barchart.missive.api.spec.TestSpec.BOOLEAN3;
import static com.barchart.missive.api.spec.TestSpec.BYTE;
import static com.barchart.missive.api.spec.TestSpec.CHARACTER;
import static com.barchart.missive.api.spec.TestSpec.DOUBLE;
import static com.barchart.missive.api.spec.TestSpec.FLOAT;
import static com.barchart.missive.api.spec.TestSpec.INTEGER;
import static com.barchart.missive.api.spec.TestSpec.LONG;
import static com.barchart.missive.api.spec.TestSpec.SHORT;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.api.spec.PrimitiveMissive;
import com.barchart.missive.fast.FastMissive;
import com.barchart.missive.fast.FastRawMissive;

public class TestRawMissive {
	
	@Test
	public void testRaw() {
		
		final FastRawMissive testRaw = new FastRawMissive();
		
		testRaw.putRaw(BYTE, "0");
		testRaw.putRaw(SHORT, "0");
		testRaw.putRaw(INTEGER, "0");
		testRaw.putRaw(LONG, "0");
		testRaw.putRaw(FLOAT, "0");
		testRaw.putRaw(DOUBLE, "0.0");
		testRaw.putRaw(BOOLEAN1, "true");
		testRaw.putRaw(BOOLEAN2, "true");
		testRaw.putRaw(BOOLEAN3, "true");
		testRaw.putRaw(CHARACTER, 'c');
		
		FastMissive primMissive = new PrimitiveMissive();
		assertTrue(testRaw.isSubsetOf(primMissive));
		
		primMissive = testRaw.cast(primMissive);
		
		System.out.println(primMissive.toString());
		
	}

}
