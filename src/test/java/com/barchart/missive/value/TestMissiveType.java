package com.barchart.missive.value;

import static com.barchart.missive.value.TestSpec.*;

public class TestMissiveType {
	
	public static void main(final String[] args) {
		
		final ExampleMissive ex = ValueMissive.build(ExampleMissive.class);
		
		for(ValueTag<?> t : ex.tags()) {
			System.out.println(t.name() + " " + t.type().size());
		}
		
		final IntMissive intMiss = ValueMissive.build(IntMissive.class);
		
		intMiss.set(INT_1, 1);
		intMiss.set(INT_2, 2);
		intMiss.set(INT_3, 3);
		
		ex.set(INT_MISSIVE, intMiss);
		
		final IntMissive intMiss2 = ex.get(INT_MISSIVE);
		
		System.out.println(intMiss2.get(INT_1));
		System.out.println(intMiss2.get(INT_2));
		System.out.println(intMiss2.get(INT_3));
		
		System.out.println(intMiss == intMiss2);
		
		
	}

}
