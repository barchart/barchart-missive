package com.barchart.missive.value.types;

import java.nio.ByteBuffer;

import com.barchart.missive.value.NullValue;
import com.barchart.missive.value.ValueType;

public class IntegerType implements ValueType<Integer> {

	public static final NullInteger NULL_INTEGER = new NullInteger();
	
	@Override
	public Integer get(final byte[] bytes, final int index) {
		return ByteBuffer.wrap(bytes).getInt(index);
	}

	@Override
	public byte[] put(final byte[] bytes, final int index, final Integer t) {
		return ByteBuffer.wrap(bytes).putInt(index, t).array();
	}

	@Override
	public boolean isNull(final byte[] bytes, final int index) {
		return this == NULL_INTEGER;
	}

	@Override
	public int size() {
		return 4;
	}
	
	@Override
	public NullValue<Integer> getNull() {
		return NULL_INTEGER;
	}
	
}
