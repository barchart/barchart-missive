package com.barchart.missive.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {

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

	public static String genericName(final Object instance, final int place)
			throws Exception {

		final ClassTrace trace = new ClassTrace();

		final Class<?> parent = trace.getClassAt(place);

		log.info("###  parent : {}", parent);

		final Field[] fieldArray = parent.getDeclaredFields();

		for (final Field field : fieldArray) {

			if (!isConstant(field)) {
				continue;
			}

			final Object filedValue = field.get(null);

			log.info("### field : {}  / {}", field, filedValue);

			if (filedValue == instance) {
				return field.getName();
			}

		}

		return "invalid name";

	}

	public static Class<?> genericParam(final Class<?> clazz) throws Exception {

		final Type type = clazz.getGenericSuperclass();

		if (!(type instanceof ParameterizedType)) {
			throw new IllegalArgumentException("invalid use of generic params");
		}

		final ParameterizedType paraType = (ParameterizedType) type;

		final Type[] typeArgs = paraType.getActualTypeArguments();

		return (Class<?>) typeArgs[0];

	}

	public static boolean isConstant(final Field field) {
		return true && //
				Modifier.isPublic(field.getModifiers()) && //
				Modifier.isStatic(field.getModifiers()) && //
				Modifier.isFinal(field.getModifiers()) //
		;

	}

	public static <T> List<T> constantFields( //
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

}
