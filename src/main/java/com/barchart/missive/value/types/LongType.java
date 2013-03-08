package com.barchart.missive.value.types;

import java.nio.ByteBuffer;

import com.barchart.missive.value.NullValue;
import com.barchart.missive.value.ValueType;

public class LongType implements ValueType<Long> {
	
	public static final NullLong NULL_LONG = new NullLong();

	@Override
	public Long get(byte[] bytes, int index) {
		return ByteBuffer.wrap(bytes).getLong(index);
	}

	@Override
	public byte[] put(byte[] bytes, int index, Long value) {
		return ByteBuffer.wrap(bytes).putLong(index, value).array();
	}

	@Override
	public boolean isNull(byte[] bytes, int index) {
		return this == NULL_LONG;
	}

	@Override
	public int size() {
		return 8;
	}

	@Override
	public NullValue<Long> getNull() {
		return NULL_LONG;
	}

}
