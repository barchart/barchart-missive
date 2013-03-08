package com.barchart.missive.value;

public interface ValueType<T> {
	
	T get(byte[] bytes, int index);
	
	byte[] put(byte[] bytes, int index, T value);
	
	boolean isNull(byte[] bytes, int index);
	
	int size();
	
	NullValue<T> getNull();

}
