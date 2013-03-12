package com.barchart.missive.value;

import com.barchart.missive.api.Tag;

public abstract class ValTag<V> implements ValueType<V>, Tag<V> {

	final ValueType<V> type;
	
	public ValTag(final ValueType<V> type) {
		this.type = type;
	}
	
	public static <V> ValTag<V> create(final Class<V> clazz, final ValueType<V> type) {
		
		return null;
		
	}
	
//	@Override
//	public V getValue(byte[] bytes, int index) {
//		return type.getValue(bytes, index);
//	}
//
//	@Override
//	public V getValue(byte[] bytes) {
//		return type.getValue(bytes);
//	}
//
//	@Override
//	public byte[] getBytes(V value) {
//		return type.getBytes(value);
//	}
//
//	@Override
//	public byte[] putValue(V value, byte[] bytes, int index) {
//		return type.putValue(value, bytes, index);
//	}
//
//	@Override
//	public boolean isNull(byte[] bytes, int index) {
//		return type.isNull(bytes, index);
//	}
//
//	@Override
//	public int size() {
//		return type.size();
//	}
//
//	@Override
//	public NullValue<V> getNull() {
//		return type.getNull();
//	}

}
