/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.extra;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;

public class Lexicon {

	protected static final Logger log = LoggerFactory.getLogger(Lexicon.class);

	private final Map<String, Tag<?>> intoTags = new HashMap<String, Tag<?>>();
	private final Map<Tag<?>, String> fromTags = new HashMap<Tag<?>, String>();

	public Lexicon(final Map<String, Tag<?>> tags) throws MissiveException {

		for (final Entry<String, Tag<?>> e : tags.entrySet()) {
			intoTags.put(e.getKey(), e.getValue());
			fromTags.put(e.getValue(), e.getKey());
		}

		if (intoTags.size() != fromTags.size()) {
			final String messasge = "Tags map not 1 to 1";
			log.error("{}", messasge);
			throw new MissiveException(messasge);
		}

	}

	public Tag<?> getTag(final String name) {
		return intoTags.get(name);
	}

	public boolean hasTag(final Tag<?> tag) {
		return intoTags.containsValue(tag);
	}

	public String getName(final Tag<?> tag) {
		return fromTags.get(tag);
	}

	public static Map<String, Tag<?>> build(final Class<?> clazz)
			throws Exception {

		final Map<String, Tag<?>> tags = new HashMap<String, Tag<?>>();

		final Field[] fieldArray = clazz.getDeclaredFields();

		for (final Field field : fieldArray) {
			if (field.getType() == Tag.class) {
				final Tag<?> tag = (Tag<?>) field.get(null);
				tags.put(tag.getName(), tag);
			}
		}

		return tags;

	}

}
