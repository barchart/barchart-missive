package com.barchart.missive.api;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.refactoring.TagHashMap;

public class TestTagHashMap {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	static Instrumentation instrumentation;

	public static void premain(final String args, final Instrumentation inst) {
		instrumentation = inst;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test() {

		final int mapSize = 20;
		final int testGetSize = 1000 * 1000;

		final Tag<?>[] tags = new Tag<?>[mapSize];

		for (int i = 0; i < mapSize; i++) {
			tags[i] = Tag.create(String.valueOf(i), String.class);
		}

		final TagHashMap testMap = new TagHashMap(tags);

		for (final Tag tag : tags) {
			testMap.set(tag, "Test" + tag.getName());
		}

		/* Warm up */
		for (int i = 0; i < testGetSize; i++) {
			testMap.get(tags[(int) Math.floor(Math.random() * mapSize)]);
		}

		/* Test our map */
		long start = System.currentTimeMillis();
		for (int i = 0; i < testGetSize; i++) {
			testMap.get(tags[(int) Math.floor(Math.random() * mapSize)]);
		}
		log.info("test our-impl = {}", System.currentTimeMillis() - start);

		/* Build java hashmap version */
		final Map<Tag<?>, Object> testJavaMap = new HashMap<Tag<?>, Object>();
		for (final Tag tag : tags) {
			testJavaMap.put(tag, tag.getName());
		}

		/* Warm up */
		for (int i = 0; i < testGetSize; i++) {
			testJavaMap.get(tags[(int) Math.floor(Math.random() * mapSize)]);
		}

		/* Test java map */
		start = System.currentTimeMillis();
		for (int i = 0; i < testGetSize; i++) {
			testJavaMap.get(tags[(int) Math.floor(Math.random() * mapSize)]);
		}
		log.info("test java-hash = {}", (System.currentTimeMillis() - start));

	}
}
