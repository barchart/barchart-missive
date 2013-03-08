package com.barchart.missive.value;

public interface Task<X extends ValueMissive, Y extends ValueMissive> {

	Void run(X x, Y y);
	
}
