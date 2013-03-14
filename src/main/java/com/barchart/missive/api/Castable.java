package com.barchart.missive.api;

public interface Castable<T extends TagMap> {
	
	<V extends T> V cast(Class<V> clazz);

}
