package com.barchart.missive.value;

import java.lang.reflect.Array;

@SuppressWarnings("rawtypes")
public class ListType<T extends ValueType> implements ValueType<T[]> {

	//private final NullList<T> NULL_LIST;
	
	private final int size;
	protected final T type;
	
	public ListType(final T type, final int size) {
		this.type = type;
		this.size = size;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] getValue(final byte[] bytes, final int index) {
		
		final T[] array = (T[]) Array.newInstance(type.getClass(), size);
		
		for(int i = 0; i < size; i++) {
			array[i] = (T) type.getValue(bytes, i * type.size());
		}
		
		return array;
	}

	@Override
	public T[] getValue(final byte[] bytes) {
		return getValue(bytes, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] getBytes(final T[] value) {
		
		final byte[] bytes = new byte[value.length * type.size()];
		
		for(int i = 0; i < value.length; i++) {
			System.arraycopy(type.getBytes(value[i]), 0, 
					bytes, i * size, type.size());
		}
		
		return bytes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public byte[] putValue(final T[] value, final byte[] bytes, final int index) {
		
		for(int i = 0; i < size; i++) {
			System.arraycopy(type.getBytes(value[i]), 0, 
					bytes, index + (i * type.size()), type.size());
		}
		
		return bytes;
	}

	@Override
	public boolean isNull(final byte[] bytes, final int index) {
		return this == getNull();
	}

	@Override
	public int size() {
		return size * type.size();
	}

	//TODO
	@Override
	public NullValue<T[]> getNull() {
		return null;
	}

}
