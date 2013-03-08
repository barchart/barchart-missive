package com.barchart.missive.value;

import java.lang.reflect.Array;

@SuppressWarnings("rawtypes")
public class NullList<T extends ValueType> extends ListType<T> implements NullValue<ListType<?>> {

	public NullList(final T type) {
		super(type, 0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] getValue(final byte[] bytes, final int index) {
		return (T[]) Array.newInstance(type.getClass(), 0);
	}
	
	@Override
	public T[] getValue(final byte[] bytes) {
		return getValue(bytes, 0);
	}

	@Override
	public byte[] getBytes(final T[] value) {
		return new byte[0];
	}
	
	@Override
	public byte[] putValue(final T[] value, final byte[] bytes, final int index) {
		return bytes;
	}
	
	@Override
	public boolean isNull(final byte[] bytes, final int index) {
		return true;
	}
	
	@Override
	public int size() {
		return 0;
	}
	
}
