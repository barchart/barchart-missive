/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.api.Tag;

/**
 * @author Gavin M Litchfield
 */
public class TestTag {

	private static final Logger log = LoggerFactory.getLogger(TestTag.class);

	private enum TestEnum {
		T1, T2
	}

	/** verify auto name */
	public static final Tag<Double> DOUBLE_FIELD = TagFactory
			.create(Double.class);

	/** verify auto name */
	public static final Tag<String> STRING_FIELD = TagFactory
			.create(String.class);

	// TODO Make into unit test
	public static void main(final String[] args) {

		final Tag<TestEnum> TestEnum = TagFactory.create("TestEag",
				TestEnum.class);

		final Tag<Byte> TestByte = TagFactory.create("TestByte", Byte.class);
		final Tag<Short> TestShort = TagFactory
				.create("TestShort", Short.class);
		final Tag<Integer> TestInteger = TagFactory.create("TestInteger",
				Integer.class);
		final Tag<Long> TestLong = TagFactory.create("TestLong", Long.class);
		final Tag<Float> TestFloat = TagFactory
				.create("TestFloat", Float.class);
		final Tag<Double> TestDouble = TagFactory.create("TestDouble",
				Double.class);
		final Tag<Boolean> TestBoolean = TagFactory.create("TestBoolean",
				Boolean.class);
		final Tag<Character> TestCharacter = TagFactory.create("TestCharacter",
				Character.class);

		// Test casting from String
		log.info(TestEnum.cast("T1").name());

		log.info(TestByte.cast("1").toString());
		log.info(TestShort.cast("2").toString());
		log.info(TestInteger.cast("3").toString());
		log.info(TestLong.cast("4").toString());
		log.info(TestFloat.cast("1.1").toString());
		log.info(TestDouble.cast("2.2").toString());
		log.info(TestCharacter.cast("X").toString());

		log.info(TestBoolean.cast("true").toString());
		log.info(TestBoolean.cast("Y").toString());
		log.info(TestBoolean.cast("y").toString());
		log.info(TestBoolean.cast("True").toString());

		log.info(TestInteger.cast(1).toString());
		log.info(TestFloat.cast(0.0f).toString());
		log.info(TestDouble.cast(0.0).toString());
		log.info(TestBoolean.cast(true).toString());

	}

	@Test
	public void testConstructProper() {

		final Tag<String> tag = TagFactory.create("hello", String.class);

		assertTrue("class match", tag.classType() == String.class);

	}

	@Test
	public void testIs() {

		assertTrue(TagFactory.isEnum(TestEnum.class));

		assertTrue(TagFactory.isList(byte[].class));
		assertTrue(TagFactory.isList(ArrayList.class));

		assertTrue(TagFactory.isPrim(Integer.class));

	}

}
