package com.barchart.missive.api;

import java.util.Collections;
import java.util.Map;

public class RawData {

	private final String name;
	
	private final Map<Integer, Object> data;
	
	public RawData(final String name, final Map<Integer, Object> data) {
		this.name = name;
		this.data = data;
	}
	
	public String name() {
		return name;
	}
	
	public Map<Integer, Object> data() {
		return Collections.unmodifiableMap(data);
	}
	
}
