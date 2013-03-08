package com.barchart.missive.value.types;

import com.barchart.missive.value.NullValue;

public class NullInteger extends IntegerType implements NullValue<Integer> {

	NullInteger() {
	
	}
	
	@Override
	public Integer getValue(final byte[] bytes, final int index) {
		return 0;
	}
	
	@Override
	public byte[] putValue(final Integer t, final byte[] bytes, final int index) {
		// TODO What makes sense here?
		return bytes;
	}
	
	@Override
	public boolean isNull(final byte[] bytes, final int index) {
		return true;
	}
	
}
