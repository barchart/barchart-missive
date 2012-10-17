package com.barchart.missive.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RawData {

	private final String name;
	
	private final Map<Integer, Object> data;
	
	public RawData(final String name) {
		this.name = name;
		data = new HashMap<Integer, Object>();
	}
	
	public RawData(final String name, final Map<Integer, Object> data) {
		this.name = name;
		this.data = data;
	}
	
	public void put(final int code, final Object val) {
		data.put(code, val);
	}
	
	public void put(final Map<Integer, Object> data) {
		data.putAll(data);
	}
	
	public String name() {
		return name;
	}
	
	public Map<Integer, Object> data() {
		return Collections.unmodifiableMap(data);
	}
	
}
