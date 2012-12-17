/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewLexicon {

	protected static final Logger log = LoggerFactory
			.getLogger(NewLexicon.class);

	protected static final Map<String, Tag<?>> toTags = new HashMap<String, Tag<?>>();
	protected static final Map<Tag<?>, String> fromTags = new HashMap<Tag<?>, String>();

	public static void build() {

		try {

			final StackTraceElement[] trace = new RuntimeException()
					.getStackTrace();
			final Class<?> clazz = Class.forName(trace[trace.length - 1]
					.getClassName());

			final Field[] fields = clazz.getFields();
			for (final Field field : fields) {
				if (field.get(null).getClass() == Tag.class) {

					final Tag<?> tag = (Tag<?>) field.get(null);
					toTags.put(tag.getName(), tag);
					fromTags.put(tag, tag.getName());
				}
			}

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
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
