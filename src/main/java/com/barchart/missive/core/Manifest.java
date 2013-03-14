package com.barchart.missive.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.barchart.missive.api.Tag;
import com.barchart.missive.api.TagMap;

/**
 * 
 * @author Gavin M Litchfield
 *
 * @param <M> the extended base class of the classes represented in 
 * the manifest.
 */
@SuppressWarnings("serial")
public class Manifest<M extends TagMap> extends HashMap<Class<? extends M>, Tag<?>[]> {
	
	public Manifest() {
	}
	
	public List<Class<? extends M>> orderedClasses() {
		final List<Class<? extends M>> classes = 
				new ArrayList<Class<? extends M>>(this.keySet());
		
		Collections.sort(classes, new TypeComparator());
		
		return classes;
	}
	
	private static class TypeComparator implements Comparator<Class<? extends TagMap>> {

		@Override
		public int compare(final Class<? extends TagMap> o1,
				final Class<? extends TagMap> o2) {
			
			return countSuperclasses(o1) - countSuperclasses(o2);
		}
		
	}
	
	private static int countSuperclasses(final Class<? extends TagMap> o1) {
		
		Class<?> temp = o1;
		int count = 0;
		while(!temp.equals(Object.class)) {
			temp = temp.getSuperclass();
			count++;
		}
		
		return count;
	}
	
}
