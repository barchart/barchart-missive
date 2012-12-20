/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.fast;

import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;
import com.barchart.missive.core.TagMapSafe;
import com.barchart.missive.core.TagMapUnsafe;

/**
 * 
 * @author Gavin M Litchfield
 * 
 */
public class FastUnsafeTagMap extends FastSafeTagMap implements TagMapUnsafe {

	protected FastUnsafeTagMap() {

	}

	public FastUnsafeTagMap(final Tag<?>... tagz) {
		super(tagz);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void putRaw(final Tag newTag, final Object newValue)
			throws MissiveException {
		put(newTag, newTag.cast(newValue));
	}

	@Override
	public <V> void put(final Tag<V> newTag, final V newValue) {

		if (contains(newTag)) {
			set(newTag, newValue);
		} else {

			final Tag<?>[] temp = new Tag<?>[tags.length + 1];
			System.arraycopy(tags, 0, temp, 0, tags.length);
			tags = temp;

			final Object[] tempValues = new Object[tags.length];
			System.arraycopy(values, 0, tempValues, 0, values.length);
			values = tempValues;

			tagsByIndex[newTag.index()] = newTag;
			indexLookup[newTag.index()] = values.length - 1;
			values[indexLookup[newTag.index()]] = newValue;

			tags[tags.length - 1] = newTag;

			size++;

		}
	}

	@Override
	@SuppressWarnings({ "rawtypes" })
	public Object remove(final Tag oldTag) {

		if (!contains(oldTag)) {
			return null;
		}

		final Object oldValue = values[indexLookup[oldTag.index()]];

		values[indexLookup[oldTag.index()]] = null;

		// This may cause problems
		for (int i = 0; i < tags.length; i++) {
			if (tags[i] == oldTag) {
				tags[i] = null;
				break;
			}
		}

		tagsByIndex[oldTag.index()] = null;

		size--;

		return oldValue;
	}

	@Override
	public boolean isSupersetOf(final TagMapSafe map) {

		for (final Tag<?> thatTag : map.tags()) {

			boolean tagCheck = false;

			for (final Tag<?> thisTag : tags) {
				if (thatTag == thisTag) {
					tagCheck = true;
				}
			}

			if (!tagCheck) {
				return false;
			}

		}

		return true;
	}

}
