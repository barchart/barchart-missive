package com.barchart.missive.value;

import java.lang.reflect.Array;

@SuppressWarnings("rawtypes")
public class ListType<V, T extends ValueType<V>> implements ValueType<V[]> {

	@SuppressWarnings("unused")
	private final NullList NULL_LIST = new NullList();
	
	private final int size;
	protected final T type;
	
	public ListType(final T type, final int size) {
		this.type = type;
		this.size = size;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V[] getValue(final byte[] bytes, final int index) {
		
		final V[] array = (V[]) Array.newInstance(type.getClass(), size);
		
		for(int i = 0; i < size; i++) {
			array[i] = (V) type.getValue(bytes, i * type.size());
		}
		
		return array;
	}

	@Override
	public V[] getValue(final byte[] bytes) {
		return getValue(bytes, 0);
	}

	@Override
	public byte[] getBytes(final V[] value) {
		
		final byte[] bytes = new byte[value.length * type.size()];
		
		for(int i = 0; i < value.length; i++) {
			System.arraycopy(type.getBytes(value[i]), 0, 
					bytes, i * size, type.size());
		}
		
		return bytes;
	}
	
	@Override
	public byte[] putValue(final V[] value, final byte[] bytes, final int index) {
		
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
	public NullValue<V[]> getNull() {
		return null; //TODO
	}
	
	@Override
	public int size() {
		return size * type.size();
	}

	private static class NullList extends ListType implements NullValue<ListType<?,?>> {

		@SuppressWarnings("unchecked")
		NullList() {
			super(null, 0);
		}
		
		@Override
		public Object[] getValue(final byte[] bytes, final int index) {
			return (Object[]) Array.newInstance(Object.class, 0);
		}
		
		@Override
		public Object[] getValue(final byte[] bytes) {
			return getValue(bytes, 0);
		}
		
		@Override
		public byte[] putValue(final Object[] value, final byte[] bytes, final int index) {
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

}
