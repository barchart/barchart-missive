package com.barchart.missive.api;

import java.util.Map;

public class NewLexicon {
	
	private final Map<String, Tag<?>> toTags;
	
	public NewLexicon(final Map<String, Tag<?>> tags) {
		toTags = tags;
	}
	
	public Tag<?> getTag(final String name) {
		return toTags.get(name);
	}
	
	public boolean hasTag(final Tag<?> tag) {
		return toTags.containsValue(tag);
	}

}
