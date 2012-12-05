package com.barchart.missive.fast;

import com.barchart.missive.Tag;
import com.barchart.missive.core.Missive;
import com.barchart.missive.core.RawMissive;


/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class FastRawMissive extends FastTagMap implements RawMissive {

	@Override
	public boolean isSupersetOf(Missive m) {
		for(final Tag<?> tag : m.getTags()) {
			if(!containsTag(tag)) {
				return false;
			}
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M cast(M m) {
		
		for(final Tag tag : m.getTags()) {
			m.set(tag, get(tag));
		}
		return m;
	}

	

}
