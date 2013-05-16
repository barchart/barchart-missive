package com.barchart.missive.api;

/**
 * 
 */
public interface Castable<T extends TagMap> {

	<V extends T> V cast(Class<V> clazz);

	<V extends T> V subclass();

	<V extends T> V superclass();

	boolean hasSubclass();

	boolean hasSuperclass();

}
