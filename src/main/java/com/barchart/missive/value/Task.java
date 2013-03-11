package com.barchart.missive.value;

public interface Task<P extends ValueMissive> {

	void run(P p);
	
}
