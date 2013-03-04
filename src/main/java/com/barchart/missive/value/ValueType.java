package com.barchart.missive.value;

import java.nio.ByteBuffer;

public interface ValueType<T extends ValueType<T>> {
	
	T get(ByteBuffer bytes, int index);
	
	T put(ByteBuffer bytes, int index, T t);
	
	boolean isNull(ByteBuffer bytes, int index);

}
