package com.barchart.missive.api;

public class Manafest  {

	private final String name;
	private final Tag<?>[] tags;
	
	public Manafest(final String name, final Tag<?>[] value) {
		this.name = name;
		this.tags = value;
	}
	
	public Tag<?>[] getTags() {
		return tags;
	}
	
	public boolean has(final Tag<?> tag) {
		for(final Tag<?> t : tags) {
			if(t.hashCode() == tag.hashCode()) {
				return true;
			}
		}
		return false;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		int code = name.hashCode();
		
		for(final Tag<?> tag : tags) {
			code = code * tag.hashCode();
		}
		
		return code;
	}
	
}
