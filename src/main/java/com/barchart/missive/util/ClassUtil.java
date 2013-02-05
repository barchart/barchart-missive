/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Reflection Utilities. */
public class ClassUtil {

	/** Provide access to stack trace. */
	public static class ClassTrace extends SecurityManager {

		public Class<?> getClassAt(final int index) {
			return getClassContext()[index];
		}

		@Override
		public Class<?>[] getClassContext() {
			return super.getClassContext();
		}

		public String getNameAt(final int index) {
			return getClassContext()[index].getName();
		}

		public void log(final int depth) {
			final Class<?>[] array = getClassContext();
			final int size = Math.min(array.length, depth);
			for (int k = 0; k < size; k++) {
				log.debug("klaz : {} {}", k, array[k].getName());
			}
		}

	}

	private static Logger log = LoggerFactory.getLogger(ClassUtil.class);

	/** Find construction location. */
	public static Class<?> instanceSpot(final int place) throws Exception {

		final ClassTrace trace = new ClassTrace();

		final Class<?> spot = trace.getClassAt(place);

		return spot;

	}

	/** Extract generic parameter. */
	public static Class<?> genericParam(final Class<?> klaz) throws Exception {

		final Type type = klaz.getGenericSuperclass();

		if (!(type instanceof ParameterizedType)) {
			throw new IllegalArgumentException("invalid use of generic params");
		}

		final ParameterizedType paraType = (ParameterizedType) type;

		final Type[] typeArgs = paraType.getActualTypeArguments();

		return (Class<?>) typeArgs[0];

	}

	/** Check for public static final field. */
	public static boolean isConstant(final Field field) {
		return true && //
				Modifier.isPublic(field.getModifiers()) && //
				Modifier.isStatic(field.getModifiers()) && //
				Modifier.isFinal(field.getModifiers()) //
		;

	}

	/** Find all container constants. */
	public static <T> List<T> constantFieldsAll( //
			final Class<?> containerType, //
			final Class<T> elementType //
	) throws Exception {
		throw new UnsupportedOperationException("TODO");
	}

	/** Find top level container constants. */
	public static <T> List<T> constantFieldsTop( //
			final Class<?> containerType, //
			final Class<T> elementType //
	) throws Exception {

		final List<T> fieldList = new ArrayList<T>();

		final Field[] fieldArray = containerType.getDeclaredFields();

		for (final Field field : fieldArray) {

			if (!isConstant(field)) {
				continue;
			}

			if (field.getType() != elementType) {
				continue;
			}

			@SuppressWarnings("unchecked")
			final T entry = (T) field.get(null);

			fieldList.add(entry);

		}

		return fieldList;

	}

	/** Find top level container constant field name. */
	public static String constantFieldName( //
			final Class<?> containerType, //
			final Object fieldInstance //
	) throws Exception {

		final Field[] fieldArray = containerType.getDeclaredFields();

		for (final Field field : fieldArray) {

			if (!isConstant(field)) {
				continue;
			}

			final Object entryInstance = field.get(null);

			if (entryInstance == fieldInstance) {
				return field.getName();
			}

		}

		throw new IllegalStateException("field not found in container ");

	}

}
