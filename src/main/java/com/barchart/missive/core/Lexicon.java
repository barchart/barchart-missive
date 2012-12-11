package com.barchart.missive.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Lexicon {
	
	private final Map<String, Tag<?>> toTags = new HashMap<String, Tag<?>>();
	private final Map<Tag<?>, String> fromTags = new HashMap<Tag<?>, String>();
	
	public Lexicon(final Map<String, Tag<?>> tags) {
		
		for(final Entry<String, Tag<?>> e : tags.entrySet()) {
			toTags.put(e.getKey(), e.getValue());
			fromTags.put(e.getValue(), e.getKey());
		}
		if(toTags.size() != fromTags.size()) {
			throw new RuntimeException("Tags map not 1 to 1");
		}
	}
	
	public Tag<?> getTag(final String name) {
		return toTags.get(name);
	}
	
	public boolean hasTag(final Tag<?> tag) {
		return toTags.containsValue(tag);
	}
	
	public String getName(final Tag<?> tag) {
		return fromTags.get(tag);
	}

	public static Map<String, Tag<?>> build(final Class<?> clazz) 
			throws IllegalArgumentException, IllegalAccessException {
		
		final Map<String, Tag<?>> tags = new HashMap<String, Tag<?>>();
		final Field[] fieldArray = clazz.getDeclaredFields();
		
		for(final Field field : fieldArray) {
			if(field.getType() == Tag.class) {
				Tag<?> tag = (Tag<?>) field.get(null);
				tags.put(tag.getName(), tag);
			}
		}
		
		return tags;
		
	}
	
}
