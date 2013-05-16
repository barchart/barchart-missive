package com.barchart.missive.api;

import com.barchart.missive.core.MissiveException;

/**
 * 
 */
public interface Translator<T> {

	TagMap toMissive(T instance) throws MissiveException;

	T fromMissive(TagMap missive) throws MissiveException;

}
