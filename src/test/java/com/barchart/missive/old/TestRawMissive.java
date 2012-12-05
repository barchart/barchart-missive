package com.barchart.missive.old;

import static com.barchart.missive.TestSpec.BOOLEAN1;
import static com.barchart.missive.TestSpec.BOOLEAN2;
import static com.barchart.missive.TestSpec.BOOLEAN3;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.SHORT;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.barchart.missive.fast.FastMissive;
import com.barchart.missive.fast.FastRawMissive;
import com.barchart.missive.spec.PrimitiveMissive;

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
		primMissive = testRaw.castAsSubclass(primMissive);
		
		System.out.println(primMissive.toString());
		
	}

}
