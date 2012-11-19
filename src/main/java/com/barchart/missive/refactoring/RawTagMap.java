package com.barchart.missive.refactoring;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public interface RawTagMap extends VarTagMap {

	@SuppressWarnings("rawtypes")
	public void putRaw(Tag tag, Object value);
	
}
