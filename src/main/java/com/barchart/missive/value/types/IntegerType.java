package com.barchart.missive.value.types;

import java.nio.ByteBuffer;

import com.barchart.missive.value.NullValue;
import com.barchart.missive.value.ValueType;

public class IntegerType implements ValueType<Integer> {

	public static final NullInteger NULL_INTEGER = new NullInteger();
	
	@Override
	public Integer getValue(final byte[] bytes, final int index) {
		return ByteBuffer.wrap(bytes).getInt(index);
	}
	
	@Override
	public Integer getValue(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt(0);
	}

	@Override
	public byte[] getBytes(final Integer value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	@Override
	public byte[] putValue(final Integer t, final byte[] bytes, final int index) {
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

	static class NullInteger extends IntegerType implements NullValue<Integer> {
		
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
	
}
