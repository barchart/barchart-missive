package com.barchart.missive.value;

public interface ValueTaskSet {

	@SuppressWarnings("rawtypes")
	<P extends ValueTag> ValueTask<P> get(P p);
	
}
