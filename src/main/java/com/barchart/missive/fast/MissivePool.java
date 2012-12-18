/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.MissiveException;

public abstract class MissivePool<M extends Missive> {

	protected static final Logger log = LoggerFactory
			.getLogger(MissivePool.class);

	private static final int DEFAULT_SIZE = 50;
	private final int size;

	private M[] pool;
	private AtomicBoolean[] usePool; // Make simple wrapper class
	private final AtomicInteger counter = new AtomicInteger(0);

	public MissivePool() {
		size = DEFAULT_SIZE;
		build();
	}

	public MissivePool(final int size) {
		this.size = size;
		build();
	}

	@SuppressWarnings("unchecked")
	private void build() throws MissiveException {

		final ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();

		final Type[] typeArgs = type.getActualTypeArguments();

		final Class<M> clazz = (Class<M>) typeArgs[0];

		try {

			clazz.newInstance();

		} catch (final Throwable e) {

			final String message = //
			"Failed to instantiate " + clazz.getName();
			log.error(message, e);
			throw new MissiveException(message, e);

		}

		pool = (M[]) Array.newInstance(clazz, size);
		usePool = new AtomicBoolean[size];

		for (int i = 0; i < size; i++) {
			final M m = clazz.cast(Missive.make(clazz));
			pool[i] = m;
			usePool[i] = pool[i].inUse;
		}

	}

	public M next() {

		if (counter.get() == size) {
			counter.set(0);
		}

		// Check if in use

		return pool[counter.getAndIncrement()];
	}

}
