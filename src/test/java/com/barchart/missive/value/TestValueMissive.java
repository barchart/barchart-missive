package com.barchart.missive.value;

import static org.junit.Assert.*;
import static com.barchart.missive.value.TestSpec.*;

import org.junit.Test;

public class TestValueMissive {
	
	
	
	@Test
	public void test() {
	
		final ExampleMissive examp = ValueMissive.build(ExampleMissive.class);
		
		examp.set(INT_1, 1);
		examp.set(INT_2, 2);
		examp.set(INT_3, 3);
		
		examp.set(LONG_1, 4l);
		examp.set(LONG_2, 5l);
		examp.set(LONG_3, 6l);
		
		assertTrue(examp.get(INT_1) == 1);
		assertTrue(examp.get(INT_2) == 2);
		assertTrue(examp.get(INT_3) == 3);
		assertTrue(examp.get(LONG_1) == 4);
		assertTrue(examp.get(LONG_2) == 5);
		assertTrue(examp.get(LONG_3) == 6);
		
		final ExampleMissive thatMiss = ValueMissive.build(ExampleMissive.class);
		
		thatMiss.set(INT_1, 3);
		thatMiss.set(INT_2, 2);
		thatMiss.set(INT_3, 1);
		
		final Functor<ExampleMissive, ExampleMissive> exAdd = examp.addInts();
		
		exAdd.run(thatMiss);
		
		assertTrue(examp.get(INT_1) == 4);
		assertTrue(examp.get(INT_2) == 4);
		assertTrue(examp.get(INT_3) == 4);
		
		
	}

}
