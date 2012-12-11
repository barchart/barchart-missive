package com.barchart.missive.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class NewLexicon {

	protected static final Map<String, Tag<?>> toTags = new HashMap<String, Tag<?>>();
	protected static final Map<Tag<?>, String> fromTags = new HashMap<Tag<?>, String>();
	
	public static void build() {
		
		try {
		
			final StackTraceElement[] trace = new RuntimeException().getStackTrace();
			final Class<?> clazz = Class.forName(trace[trace.length - 1].getClassName());
			
			Field[] fields = clazz.getFields();
			for(final Field field : fields) {
				if(field.get(null).getClass() == Tag.class) {
					
					final Tag<?> tag = (Tag<?>) field.get(null);
					toTags.put(tag.getName(), tag);
					fromTags.put(tag, tag.getName());
				}
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Tag<?> getTag(final String name) {
		return toTags.get(name);
	}
	
	public static boolean hasTag(final Tag<?> tag) {
		return toTags.containsValue(tag);
	}
	
	public static String getName(final Tag<?> tag) {
		return fromTags.get(tag);
	}
	
	
}
