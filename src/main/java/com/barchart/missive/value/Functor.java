package com.barchart.missive.value;

@SuppressWarnings("rawtypes")
public interface Functor<P extends ValueTag, R> {
	
	R run(P p);

}
