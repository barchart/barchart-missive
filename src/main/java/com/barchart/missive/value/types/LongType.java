package com.barchart.missive.value.types;

import java.nio.ByteBuffer;

import com.barchart.missive.value.NullValue;
import com.barchart.missive.value.ValueType;

public class LongType implements ValueType<Long> {
	
	public static final NullLong NULL_LONG = new NullLong();

	@Override
	public Long getValue(byte[] bytes, int index) {
		return ByteBuffer.wrap(bytes).getLong(index);
	}
	
	@Override
	public Long getValue(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong(0);
	}

	@Override
	public byte[] getBytes(Long value) {
		return ByteBuffer.allocate(0).putLong(value).array();
	}

	@Override
	public byte[] putValue(Long value, byte[] bytes, int index) {
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

	static class NullLong extends LongType implements NullValue<Long> {
		
		@Override
		public Long getValue(final byte[] bytes, final int index) {
			return 0l;
		}
		
		@Override
		public boolean isNull(byte[] bytes, int index) {
			return true;
		}
		
	}
	

}
