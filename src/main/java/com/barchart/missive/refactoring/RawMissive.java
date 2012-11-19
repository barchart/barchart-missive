package com.barchart.missive.refactoring;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class RawMissive extends VarMissive implements RawTagMap {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void putRaw(final Tag tag, final Object value) {
		values.put(tag, tag.cast(value));
	}

}
