package com.barchart.missive.api;

import com.barchart.missive.core.MissiveException;

public interface Translator<T> {

	public TagMap toMissive(T t) throws MissiveException;
	
	public T fromMissive(TagMap mis) throws MissiveException;
	
}
