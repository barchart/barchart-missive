package com.barchart.missive.value;

public interface ValueType<T> {
	
	T getValue(byte[] bytes, int index);
	
	T getValue(byte[] bytes);
	
	byte[] getBytes(T value);
	
	byte[] putValue(T value, byte[] bytes, int index);
	
	boolean isNull(byte[] bytes, int index);
	
	int size();
	
	NullValue<T> getNull();

}
