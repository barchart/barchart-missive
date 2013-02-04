/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMapSafe;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public class FastSafeTagMap implements TagMapSafe {

	protected static final Logger log = LoggerFactory
			.getLogger(FastSafeTagMap.class);

	private static final int DEFAULT_SIZE = 30;

	protected volatile Tag<?>[] tags;
	protected Object[] values;

	protected volatile Tag<?>[] tagsByIndex = new Tag<?>[Tag.maxIndex()];
	protected volatile int[] indexLookup = new int[Tag.maxIndex()];

	protected volatile int maxTagCode = 0;
	protected volatile int size = 0;

	protected FastSafeTagMap() {
		this(DEFAULT_SIZE);
	}

	public FastSafeTagMap(final int initialSize) {
		tags = new Tag<?>[initialSize];
		values = new Object[initialSize];
	}

	public FastSafeTagMap(final Tag<?>... tagz) {

		tags = tagz;
		size = tagz.length;

		values = new Object[tagz.length];

		int index = 0;
		for (final Tag<?> tag : tagz) {
			tagsByIndex[tag.index()] = tag;
			indexLookup[tag.index()] = index;
			index++;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(final Tag<V> tag) {
		return (V) values[indexLookup[tag.index()]];
	}

	@Override
	public <V> void set(final Tag<V> tag, final V value)
			throws MissiveException {
		if (contains(tag)) {
			values[indexLookup[tag.index()]] = value;
		} else {
			final String message = "Tag not in map : " + tag.getName();
			log.error("{}", message);
			throw new MissiveException(message);
		}
	}

	@Override
	public boolean contains(final Tag<?> tag) {
		return tagsByIndex[tag.index()] != null;
	}

	@Override
	public Tag<?>[] tags() {
		return tags.clone();
	}

	@Override
	public int size() {
		return size;
	}

	protected static <T> T[] concat(final T[] first, final T[] second) {
		final T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

}
