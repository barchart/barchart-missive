package com.barchart.missive.value.types;

import com.barchart.missive.value.NullValue;

public class NullInteger extends IntegerType implements NullValue<Integer> {

	NullInteger() {
	
	}
	
	@Override
	public Integer get(final byte[] bytes, final int index) {
		return 0;
	}
	
	@Override
	public byte[] put(final byte[] bytes, final int index, final Integer t) {
		// TODO What makes sense here?
		return bytes;
	}
	
	@Override
	public boolean isNull(final byte[] bytes, final int index) {
		return true;
	}
	
}
