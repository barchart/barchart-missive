/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.missive.hash;

import com.barchart.missive.core.Missive;
import com.barchart.missive.core.MissiveException;
import com.barchart.missive.core.Tag;

/**
 * A Java Collections hash map backed implementation.
 * 
 * @author Gavin M Litchfield
 * 
 */
public class HashMissive extends HashSafeTagMap implements Missive {

	public HashMissive(final Tag<?>[] tags) {
		super(tags);
	}

	/** FIXME */
	@Override
	public <M extends Missive> boolean isCastableTo(final M m) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M castAsSubclass(final M missive)
			throws MissiveException {

		for (final Tag tag : missive.tags()) {
			missive.set(tag, get(tag));
		}

		return missive;

	}

}
