package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;

public class Raw {

	private final Map<Tag<?>, Object> values =
			new HashMap<Tag<?>, Object>();
	
	public <V> void put(final Tag<V> tag, final V value) {
		values.put(tag, value);
	}
	
	public boolean satisfies(final Manifest manifest) {
		
		for(final Tag<?> tag : manifest.getTags()) {
			if(!values.containsKey(tag)) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M as(final M missive) {
		
		for(final Tag tag : missive.getManifest().getTags()) {
			missive.set(tag, values.get(tag));
		}
		return missive;
	}
	
}
