package com.barchart.missive.fast;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ObjectPool<V> {

	private static final int DEFAULT_SIZE = 50;
	private final int size;
	
	private V[] pool;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public ObjectPool() {
		size = DEFAULT_SIZE;
		build();
	}
	
	public ObjectPool(final int size) {
		this.size = size;
		build();
	}
	
	@SuppressWarnings("unchecked")
	private void build() {
		
		final ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		final Type[] typeArgs = type.getActualTypeArguments();
		
		final Class<V> valueClass = (Class<V>) typeArgs[0];
		
		pool = (V[])Array.newInstance(valueClass, size);
		
		for(int i = 0; i < size; i++) {
			try { // Needs some work
				pool[i] = valueClass.newInstance(); // Generic constructor
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public V next() {
		if(counter.get() == size) {
			counter.set(0);
		}
		return pool[counter.getAndIncrement()];
	}
	
}
