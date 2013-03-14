package com.barchart.missive.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.barchart.missive.api.Tag;

@SuppressWarnings("serial")
public class Manifest extends HashMap<Class<? extends ObjectMap>, Tag<?>[]> {
	
	public Manifest() {
	}
	
	public List<Class<? extends ObjectMap>> orderedClasses() {
		final List<Class<? extends ObjectMap>> classes = 
				new ArrayList<Class<? extends ObjectMap>>(this.keySet());
		
		Collections.sort(classes, new TypeComparator());
		
		return classes;
	}
	
	private static class TypeComparator implements Comparator<Class<? extends ObjectMap>> {

		@Override
		public int compare(final Class<? extends ObjectMap> o1,
				final Class<? extends ObjectMap> o2) {
			
			return countSuperclasses(o1) - countSuperclasses(o2);
		}
		
	}
	
	private static int countSuperclasses(final Class<? extends ObjectMap> o1) {
		
		Class<?> temp = o1;
		int count = 0;
		while(!temp.equals(ObjectMap.class)) {
			temp = temp.getSuperclass();
			count++;
		}
		
		return count;
	}
	
}
