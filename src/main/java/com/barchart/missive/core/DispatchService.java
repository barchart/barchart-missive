package com.barchart.missive.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class exists because Java cannot do multiple dispatch.
 * 
 * -Dsun.reflect.inflationThreshold = 1
 * 
 * @author Gavin M Litchfield
 */
public class DispatchService {

	private static final Logger log = LoggerFactory
			.getLogger(DispatchService.class);

	private static final String DISPATCH = "dispatch";

	private final Object obj;
	private final Method[] methods;

	public DispatchService(final Object obj) {

		if (obj == null) {
			throw new IllegalArgumentException("Object cannot be null");
		}

		this.obj = obj;
		final Class<?> clazz = obj.getClass();
		final Method[] clazzMeths = clazz.getDeclaredMethods();
		final Map<Integer, Method> map = new HashMap<Integer, Method>();
		int maxClassCode = 0;

		for (final Method m : clazzMeths) {

			m.setAccessible(true);

			if (m.getName().equals(DISPATCH)) {

				final Class<?>[] params = m.getParameterTypes();
				if (params != null && params.length == 1
						&& ObjectMap.class.isAssignableFrom(params[0])) {

					final int classCode = ObjectMapFactory.classMap
							.get(params[0]);
					map.put(classCode, m);
					if (classCode > maxClassCode) {
						maxClassCode = classCode;
					}
				}

			}

		}

		methods = new Method[maxClassCode + 1];

		for (final Entry<Integer, Method> e : map.entrySet()) {
			methods[e.getKey()] = e.getValue();
		}

	}

	public void dispatch(final ObjectMap map) {
		try {
			methods[map.classCode].invoke(obj, map);
		} catch (final Throwable e) {
			log.error("Dispatch failure.", e);
			throw new MissiveException(e);
		}
	}

}
