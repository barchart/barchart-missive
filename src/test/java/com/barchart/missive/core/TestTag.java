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

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public class TestTag {

	private static final Logger log = LoggerFactory.getLogger(TestTag.class);

	private enum TestEnum {
		T1, T2
	}

	/** verify auto name */
	public static final Tag<Double> DOUBLE_FIELD = Tag.create(Double.class);

	/** verify auto name */
	public static final Tag<String> STRING_FIELD = new Tag<String>() {
	};

	// TODO Make into unit test
	public static void main(final String[] args) {

		final Tag<TestEnum> TestEnum = new Tag<TestEnum>("TestEag",
				TestEnum.class);

		final Tag<Byte> TestByte = new Tag<Byte>("TestByte", Byte.class);
		final Tag<Short> TestShort = new Tag<Short>("TestShort", Short.class);
		final Tag<Integer> TestInteger = new Tag<Integer>("TestInteger",
				Integer.class);
		final Tag<Long> TestLong = new Tag<Long>("TestLong", Long.class);
		final Tag<Float> TestFloat = new Tag<Float>("TestFloat", Float.class);
		final Tag<Double> TestDouble = new Tag<Double>("TestDouble",
				Double.class);
		final Tag<Boolean> TestBoolean = new Tag<Boolean>("TestBoolean",
				Boolean.class);
		final Tag<Character> TestCharacter = new Tag<Character>(
				"TestCharacter", Character.class);

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

	@Test(expected = MissiveException.class)
	public void testConstructInvalid() {

		final Tag<?> tag = new Tag("hello");

		fail("invalid usage");

	}

	@Test
	public void testConstructProper() {

		final Tag<String> tag = new Tag<String>("hello") {
		};

		assertTrue("class match", tag.type() == String.class);

	}

	@Test
	public void testLazyName() {

		assertEquals("names match", STRING_FIELD.name(), "STRING_FIELD");

		assertEquals("names match", DOUBLE_FIELD.name(), "DOUBLE_FIELD");

	}

	@Test
	public void testIs() {

		assertTrue(Tag.isEnum(TestEnum.class));

		assertTrue(Tag.isList(byte[].class));
		assertTrue(Tag.isList(ArrayList.class));

		assertTrue(Tag.isPrim(Integer.class));

	}

}