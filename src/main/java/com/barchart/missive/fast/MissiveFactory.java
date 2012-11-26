package com.barchart.missive.fast;

import java.util.Map;

import com.barchart.missive.core.Missive;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class MissiveFactory {

	private static final Map<String, FastMissive> missives = FastMissive.missives;
	
	protected void put(final FastMissive m) {
		
		missives.put(m.getClass().getName(), m);
		
	}
	
	public static Missive make(final FastMissive m) {
		
		final FastMissive toClone = missives.get(m.getClass().getName());
		
		return new FastMissive(toClone.tagList, toClone.tags, toClone.maxTagCode);
		
	}
	
}
