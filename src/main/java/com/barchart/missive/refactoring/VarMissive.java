package com.barchart.missive.refactoring;

import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class VarMissive extends Missive implements VarTagMap {

	@Override
	public <V> void set(final Tag<V> tag, final V value) {
		set(tag, value);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <M extends VarMissive> M cast(final M m) {
		for(final Tag tag : m.getTags()) {
			m.set(tag,  values.get(tag));
		}
		
		return m;
	}

}
