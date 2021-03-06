package com.barchart.missive.core;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMapSafe;

/**
 *
 */
public abstract class ObjectMapSafe extends ObjectMap implements TagMapSafe {

	@Override
	public <V> void set(final Tag<V> tag, final V value)
			throws MissiveException {
		super.set(tag, value);
	}

}
