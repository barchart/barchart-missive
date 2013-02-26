/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMapUnsafe;
import com.barchart.missive.extra.OldMissive;
import com.barchart.missive.util.ClassUtil;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public abstract class FastMissive implements OldMissive {

	protected static final Logger log = LoggerFactory
			.getLogger(FastMissive.class);

	private static final Map<Class<?>, FastMissive> missives = new HashMap<Class<?>, FastMissive>();

	protected volatile Tag<?>[] publicTags = new Tag<?>[0];
	protected volatile Tag<?>[] publicTagsByIndex = new Tag<?>[0];

	protected volatile int maxTagCode = 0;

	protected TagMapUnsafe tagMap = new FastUnsafeTagMap();
	protected final AtomicBoolean inUse = new AtomicBoolean(false);

	protected FastMissive() {

	}

	protected void build(final Tag<?>... tags) {

		publicTags = tags.clone();

		for (final Tag<?> tag : tags) {
			if (maxTagCode < tag.index()) {
				maxTagCode = tag.index();
			}
		}

		publicTagsByIndex = new Tag<?>[maxTagCode + 1];

		for (final Tag<?> tag : tags) {
			publicTagsByIndex[tag.index()] = tag;
		}

		missives.put(this.getClass(), this);

	}

	protected void build(final List<Tag<?>> tags) {

		build(tags.toArray(new Tag[0]));

	}

	/**
	 * Constructor to be used by subclasses
	 * 
	 * @param tags
	 */
	protected FastMissive(final Tag<?>... tags) {

		build(tags);

	}

	protected FastMissive(final List<Tag<?>> tags) {

		build(tags);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected FastMissive(final Class<?> container) throws MissiveException {
		try {

			final List tags = //
			ClassUtil.constantFieldsTop(container, Tag.class);

			log.debug("tags = {}", tags);

			build(tags);

		} catch (final Throwable e) {
			log.error("", e);
			throw new MissiveException(e);
		}
	}

	/**
	 * Create a new instance of missive reusing keys via flyweight pattern.
	 * 
	 * @return
	 */
	public static <M extends FastMissive> M create(final Class<M> clazz)
			throws MissiveException {

		try {

			final FastMissive template = missives.get(clazz);

			final M missive = clazz.newInstance();
			missive.publicTags = template.publicTags;
			missive.publicTagsByIndex = template.publicTagsByIndex;
			missive.maxTagCode = template.maxTagCode;
			missive.tagMap = template.tagMap;

			return missive;

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
	protected void include(final Tag<?>... tags) {

		int newMaxTagCode = 0;
		for (final Tag<?> tag : tags) {
			if (newMaxTagCode < tag.index()) {
				newMaxTagCode = tag.index();
			}
		}

		if (newMaxTagCode > maxTagCode) {
			publicTagsByIndex = concat(//
					publicTagsByIndex, //
					new Tag<?>[newMaxTagCode - maxTagCode + 1]);
			maxTagCode = newMaxTagCode;
		}

		for (final Tag<?> tag : tags) {
			publicTagsByIndex[tag.index()] = tag;
		}

		publicTags = concat(publicTags, tags);

	}

	@Override
	public <V> V get(final Tag<V> tag) throws MissiveException {

		if (tag.index() > maxTagCode) {
			final String message = "Tag not visible in missive : " + tag;
			log.error("{}", message);
			throw new MissiveException(message);
		}

		if (publicTagsByIndex[tag.index()] == null) {
			final String message = "Tag not visible in missive : " + tag;
			log.error("{}", message);
			throw new MissiveException(message);
		}

		return tagMap.get(tag);

	}

	public Tag<?>[] getPublicTags() {
		return publicTags.clone();
	}

	@Override
	public int size() {
		return publicTags.length;
	}

	protected static <T> T[] concat(final T[] first, final T[] second) {
		final T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	@Override
	public <M extends OldMissive> boolean isCastableTo(final M missive) {
		// TODO Auto-generated method stub
		return false;
	}

	/** FIXME */
	@Override
	public <M extends OldMissive> M castAsSubclass(final M missive)
			throws MissiveException {

		// check if good to cast
		// m.map = map;
		// inUse.set(false);
		// return m;

		throw new UnsupportedOperationException("TODO");

	}

	@Override
	public <V> void set(final Tag<V> tag, final V value)
			throws MissiveException {

	}

	/** FIXME */
	@Override
	public boolean contains(final Tag<?> tag) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public Tag<?>[] tags() {
		return publicTags.clone();
	}

}
