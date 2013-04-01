package com.barchart.missive.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticDispatchService<C> {

	private static final Logger log = LoggerFactory.getLogger(StaticDispatchService.class);
	
	public static String DISPATCH = "dispatch";
	
	private final Method[] methods;
	
	public StaticDispatchService(final Class<C> clazz) {
		
		if(clazz == null) {
			throw new IllegalArgumentException("Object cannot be null");
		}
		
		final Method[] clazzMeths = clazz.getDeclaredMethods();
		final Map<Integer, Method> map = new HashMap<Integer, Method>();
		int maxClassCode = 0;
		
		for(final Method m : clazzMeths) {
			
			m.setAccessible(true);
			
			if(m.getName().equals(DISPATCH)) {
				
				Class<?>[] params = m.getParameterTypes();
				if(params != null && params.length == 1 && 
						ObjectMap.class.isAssignableFrom(params[0])) {
					
					int classCode = ObjectMapFactory.classMap.get(params[0]);
					map.put(classCode, m);
					if(classCode > maxClassCode) {
						maxClassCode = classCode;
					}
				}
				
			}
			
		}

		methods = new Method[maxClassCode + 1];
		
		for(final Entry<Integer, Method> e : map.entrySet()) {
			methods[e.getKey()] = e.getValue();
		}
		
	}
	
	public void dispatch(final C instance, final ObjectMap map) {
		
		try {
			methods[map.classCode].invoke(instance, map);
		} catch (IllegalArgumentException e) {
			log.error("IllegalArgumentException in dispatch");
			throw new MissiveException(e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException in dispatch");
			throw new MissiveException(e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException in dispatch");
			throw new MissiveException(e);
		}
		
	}
	
}
