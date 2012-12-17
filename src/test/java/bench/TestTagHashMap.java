/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMap;
import com.barchart.missive.fast.FastTagMap;

public class TestTagHashMap {

	protected static final Logger log = LoggerFactory.getLogger(TestTagHashMap.class);

	static Instrumentation instrumentation;

	public static void premain(final String args, final Instrumentation inst) {
		instrumentation = inst;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(final String[] args) {

		final int mapSize = 100;
		final int testGetSize = 10 * 1000 * 1000;

		final Tag<?>[] tags = new Tag<?>[mapSize];

		for (int i = 0; i < mapSize; i++) {
			tags[i] = Tag.create(String.valueOf(i), String.class);
		}

		final TagMap testMap = new FastTagMap(tags);

		for (final Tag tag : tags) {
			testMap.set(tag, "Test" + tag.getName());
		}

		/* Warm up */
		for (int i = 0; i < testGetSize; i++) {
			testMap.get(tags[i % mapSize]);
		}

		/* Test our map */
		long start = System.currentTimeMillis();
		for (int i = 0; i < testGetSize; i++) {
			testMap.get(tags[i % mapSize]);
		}
		log.info("test fast-impl = {}", System.currentTimeMillis() - start);

		/* Build java hashmap version */
		final Map<Tag<?>, Object> testJavaMap = new HashMap<Tag<?>, Object>();
		for (final Tag tag : tags) {
			testJavaMap.put(tag, tag.getName());
		}

		/* Warm up */
		for (int i = 0; i < testGetSize; i++) {
			testJavaMap.get(tags[i % mapSize]);
		}

		/* Test java map */
		start = System.currentTimeMillis();
		for (int i = 0; i < testGetSize; i++) {
			testJavaMap.get(tags[i % mapSize]);
		}
		log.info("test java-hash = {}", (System.currentTimeMillis() - start));

	}
}
