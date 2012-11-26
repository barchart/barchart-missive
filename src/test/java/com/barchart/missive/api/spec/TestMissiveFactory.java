package com.barchart.missive.api.spec;

import com.barchart.missive.core.Missive;
import com.barchart.missive.fast.MissiveFactory;

public class TestMissiveFactory {

	public static void main(final String[] args) {
		
		Missive m = MissiveFactory.make(new PrimitiveMissive());
		
		System.out.println(m.toString());
		
		Missive xm = MissiveFactory.make(new ExtendedPrimitiveMissive());
		
		System.out.println(xm.toString());
		
		final int testSize = 10 * 1000 * 1000;
		
		final PrimitiveMissive prim = new PrimitiveMissive();
		
		for(int i = 0; i > testSize; i++) {
			Missive test = MissiveFactory.make(prim);
		}
		
		long start = System.nanoTime();
		for(int i = 0; i > testSize; i++) {
			Missive test = MissiveFactory.make(prim);
		}
		System.out.println(System.nanoTime() - start);
		
		
		for(int i = 0; i > testSize; i++) {
			Missive test = new PrimitiveMissive();
		}
		
		start = System.nanoTime();
		for(int i = 0; i > testSize; i++) {
			Missive test = new PrimitiveMissive();
		}
		System.out.println(System.nanoTime() - start);
	}
	
}
