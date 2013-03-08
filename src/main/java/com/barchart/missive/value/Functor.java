package com.barchart.missive.value;

public interface Functor<P extends ValueMissive, R extends ValueMissive> {
	
	R run(P p);

}
