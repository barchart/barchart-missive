package com.barchart.missive.fast;

import java.util.HashMap;
import java.util.Map;

import com.barchart.missive.core.TagMap;

public class MissiveFactory {
	
	private static final Map<String, Missive> missives = new HashMap<String, Missive>();
	
	protected static void put(final Missive m) {
		
		missives.put(m.getClass().getName(), m);
		
	}
	
	/**
	 * Returns a missive of the type
	 * 
	 * @param m
	 * @param map
	 * @return
	 */
	public static Missive make(final Missive m, final TagMap map) {
		
		final Missive toClone = missives.get(m.getClass().getName());
		
		// Just populate the missive passed in?  
		
		return new Missive(toClone.visibleTags, toClone.visTagsByIndex, 
				toClone.maxTagCode, toClone.map);
		
	}
	
}
