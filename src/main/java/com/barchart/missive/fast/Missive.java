/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMap;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public abstract class Missive {

	protected static final Logger log = LoggerFactory.getLogger(Missive.class);

	private static final Map<Class<?>, Missive> missives = new HashMap<Class<?>, Missive>();

	protected volatile Tag<?>[] visTags = new Tag<?>[0];
	protected volatile Tag<?>[] visTagsByIndex = new Tag<?>[0];

	protected volatile int maxTagCode = 0;

	protected TagMap map = new FastTagMap();
	protected final AtomicBoolean inUse = new AtomicBoolean(false);

	protected Missive() {

	}

	/**
	 * Constructor to be used by subclasses
	 * 
	 * @param tags
	 */
	protected Missive(final Tag<?>[] tags) {

		visTags = tags.clone();

		for (final Tag<?> tag : tags) {
			if (maxTagCode < tag.index()) {
				maxTagCode = tag.index();
			}
		}

		visTagsByIndex = new Tag<?>[maxTagCode + 1];

		for (final Tag<?> tag : tags) {
			visTagsByIndex[tag.index()] = tag;
		}

		missives.put(this.getClass(), this);
	}

	/**
	 * Create a new instance of missive reusing keys via flyweight pattern.
	 * 
	 * @return
	 */
	public static <M extends Missive> M make(final Class<M> clazz)
			throws MissiveException {

		try {

			final Missive template = missives.get(clazz);

			final M newM = clazz.newInstance();
			newM.visTags = template.visTags;
			newM.visTagsByIndex = template.visTagsByIndex;
			newM.maxTagCode = template.maxTagCode;
			newM.map = template.map;

			return newM;

		} catch (final Throwable e) {

			final String message = "Failed to make " + clazz.getName();
			log.error(message, e);
			throw new MissiveException(message, e);

		}

	}

	/**
	 * Utility method for building classes which extend this class
	 * 
	 * @param tags
	 */
	protected void include(final Tag<?>[] tags) {

		int newMaxTagCode = 0;
		for (final Tag<?> tag : tags) {
			if (newMaxTagCode < tag.index()) {
				newMaxTagCode = tag.index();
			}
		}

		if (newMaxTagCode > maxTagCode) {
			visTagsByIndex = concat(visTagsByIndex, new Tag<?>[newMaxTagCode
					- maxTagCode + 1]);
			maxTagCode = newMaxTagCode;
		}

		for (final Tag<?> tag : tags) {
			visTagsByIndex[tag.index()] = tag;
		}

		visTags = concat(visTags, tags);

	}

	public <V> V get(final Tag<V> tag) throws MissiveException {

		if (tag.index() > maxTagCode) {
			final String message = "Tag not visible in missive : " + tag;
			log.error("{}", message);
			throw new MissiveException(message);
		}

		if (visTagsByIndex[tag.index()] == null) {
			final String message = "Tag not visible in missive : " + tag;
			log.error("{}", message);
			throw new MissiveException(message);
		}

		return map.get(tag);
	}

	public <M extends Missive> M castAsSubclass(final M m) {

		// check if good to cast
		m.map = map;

		inUse.set(false);
		return m;
	}

	public Tag<?>[] getVisibleTags() {
		return visTags.clone();
	}

	public int size() {
		return visTags.length;
	}

	protected static <T> T[] concat(final T[] first, final T[] second) {
		final T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

}
