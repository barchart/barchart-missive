/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.util.ClassUtil;

/**
 * 
 * @author Gavin M Litchfield
 * 
 * @param <V>
 *            The class type of the value this tag represents.
 * 
 */
public class Tag<V> {

	private static AtomicInteger counter = new AtomicInteger(0);

	public static final Logger log = LoggerFactory.getLogger(Tag.class);

	private static final Set<Class<?>> primitives = new HashSet<Class<?>>();

	static {
		primitives.add(Byte.class);
		primitives.add(Short.class);
		primitives.add(Integer.class);
		primitives.add(Long.class);
		primitives.add(Float.class);
		primitives.add(Double.class);
		primitives.add(Boolean.class);
		primitives.add(Character.class);
	}

	public static <V> Tag<V> create(final Class<V> clazz) {
		return new Tag<V>(clazz);
	}

	public static <V> Tag<V> create(final String name, final Class<V> clazz) {
		return new Tag<V>(name, clazz);
	}

	protected static boolean isEnum(final Class<?> clazz) {
		return clazz.isEnum();
	}

	protected static boolean isList(final Class<?> clazz) {
		if (clazz.isArray()) {
			return true;
		} else if (clazz.isAssignableFrom(Collection.class)) {
			return true;
		} else {
			return false;
		}
	}

	protected static boolean isPrim(final Class<?> clazz) {
		return primitives.contains(clazz);
	}

	public static int maxIndex() {
		return counter.get();
	}

	protected static int nameHash(final Class<?> clazz) {
		return clazz.getName().hashCode();
	}

	protected static int nextIndex() {
		return counter.getAndIncrement();
	}

	private static Object parsePrimitiveFromString(final Class<?> clazz,
			final String value) {

		if (clazz == Byte.class) {

			return Byte.parseByte(value);

		} else if (clazz == Short.class) {

			return Short.parseShort(value);

		} else if (clazz == Integer.class) {

			return Integer.parseInt(value);

		} else if (clazz == Long.class) {

			return Long.parseLong(value);

		} else if (clazz == Float.class) {

			return Float.parseFloat(value);

		} else if (clazz == Double.class) {

			return Double.parseDouble(value);

		} else if (clazz == Boolean.class) {

			/** note : null for false */

			if ("true".equalsIgnoreCase(value)) {
				return true;
			}
			if ("y".equalsIgnoreCase(value)) {
				return true;
			}
			if ("yes".equalsIgnoreCase(value)) {
				return true;
			}
			return false;

		} else if (clazz == Character.class) {

			return new Character(value.charAt(0));

		} else {

			throw new MissiveException("parse failure " + clazz);

		}

	}

	private final Class<V> clazz;

	private final int hashCode = nameHash(getClass());

	private final int index = nextIndex();

	private final boolean isEnum;

	private final boolean isList;

	private final boolean isPrim;

	private final String name;

	/** advanced constructor */
	@SuppressWarnings("unchecked")
	public Tag() throws MissiveException {
		try {

			this.name = defaultTagName();
			this.clazz = (Class<V>) ClassUtil.genericParam(getClass());

			isPrim = isPrim(clazz);
			isEnum = isEnum(clazz);
			isList = isList(clazz);

		} catch (final Throwable e) {
			log.error("construct failure", e);
			throw new MissiveException(e);
		}
	}

	public Tag(final Class<V> clazz) {

		this.name = defaultTagName();
		this.clazz = clazz;

		isPrim = isPrim(clazz);
		isEnum = isEnum(clazz);
		isList = isList(clazz);

	}

	/** advanced constructor */
	@SuppressWarnings("unchecked")
	public Tag(final String name) throws MissiveException {
		try {

			this.name = name;
			this.clazz = (Class<V>) ClassUtil.genericParam(getClass());

			isPrim = isPrim(clazz);
			isEnum = isEnum(clazz);
			isList = isList(clazz);

		} catch (final Throwable e) {
			log.error("construct failure", e);
			throw new MissiveException(e);
		}
	}

	public Tag(final String name, final Class<V> clazz) {

		this.name = name;
		this.clazz = clazz;

		isPrim = isPrim(clazz);
		isEnum = isEnum(clazz);
		isList = isList(clazz);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public V cast(final Object o) throws MissiveException {
		try {

			/* If the class is enum and object is string, attempt a valueOf */
			if (isEnum && o instanceof String) {

				final Class<? extends Enum> crazz = (Class<? extends Enum>) clazz;
				return (V) Enum.valueOf(crazz, (String) o);

				/* If class is primitive and object is string, attempt to parse */
			} else if (isPrim && o instanceof String) {

				return (V) parsePrimitiveFromString(clazz, (String) o);

			} else {

				try {
					/* Attempt a normal cast */
					return clazz.cast(o);
				} catch (final ClassCastException e) {
					/*
					 * Last ditch, attempt to find constructor which accepts
					 * object o
					 */
					return clazz.getConstructor(o.getClass()).newInstance(o);
				}
			}

		} catch (final Throwable e) {

			final String message = //
			"Failed to cast object in tag " + name + " " + o;
			log.error(message, e);
			throw new MissiveException(message, e);

		}
	}

	protected String defaultTagName() {
		return "TAG=" + index();
	}

	public Class<V> getClazz() {
		return clazz;
	}

	public final String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public int index() {
		return index;
	}

	public final boolean isEnum() {
		return isEnum;
	}

	public final boolean isList() {
		return isList;
	}

	public final boolean isPrimitive() {
		return isPrim;
	}

	@Override
	public String toString() {
		return name;
	}

}
