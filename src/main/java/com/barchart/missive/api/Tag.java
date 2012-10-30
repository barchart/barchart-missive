package com.barchart.missive.api;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Gavin M Litchfield
 *
 * @param <V>
 */
public class Tag<V> {
	
	private static final Set<Class<?>> primitives = new HashSet<Class<?>>();
	static {
		primitives.add(Byte.class);
		primitives.add(Short.class);
		primitives.add(Integer.class);
		primitives.add(Long.class);
		primitives.add(Float.class);
		primitives.add(Double.class);
		primitives.add(Boolean.class);
		primitives.add(Character.class);
	}
	
	protected final String name;
	private final Manifest manifest; 
	private final Class<V> clazz;
	private final boolean isPrim;
	private final boolean isComplex;
	private final boolean isEnum;
	
	public Tag(final String name, final Class<V> clazz) {
		this.name = name;
		manifest = new Manifest(name, new Tag<?>[]{this});
		this.clazz = clazz;
		isPrim = primitives.contains(clazz);
		isEnum = clazz.isEnum();
		if(clazz.isAssignableFrom(RawSet.class)) {
			isComplex = true;
		} else {
			isComplex = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Tag(final String name, final Manifest manifest) {
		this.name = name;
		this.manifest = manifest;
		clazz = (Class<V>) RawSet.class;
		isPrim = false;
		isComplex = true;
		isEnum = false;
	}

	public final String getName() {
		return name;
	}
	
	public Class<V> getClazz() {
		return clazz;
	}
	
	@Deprecated
	public Manifest manifest() {
		return manifest;
	}
	
	public final boolean isPrimitive() {
		return isPrim;
	}
	
	public final boolean isComplex() {
		return isComplex;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public V cast(final Object o) throws MissiveException {
		try {
			
			/* If the class is enum and object is string, attempt a valueOf */
			if(isEnum && o instanceof String) {
				
				final Class<? extends Enum> crazz = (Class<? extends Enum>) clazz;
				return (V) Enum.valueOf(crazz, (String) o);
			
			/* If class is primitive and object is string, attempt to parse */
			} else if(isPrim && o instanceof String) {
				
				return (V) parsePrimitiveFromString(clazz, (String) o);
				
			} else {
			
				try {
					/* Attempt a normal cast */
					return clazz.cast(o);
				} catch(final ClassCastException e) {
					/* Last ditch, attempt to find constructor which accepts object o */
					return clazz.getConstructor(o.getClass()).newInstance(o);
				}
			}
			
		} catch(final Exception e) {
			
			System.out.println("Failed to cast object in tag " + name + " " + o.toString());
			e.printStackTrace();
			throw new MissiveException("Failed to cast object in tag " + name);
			
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	private static Object parsePrimitiveFromString(final Class<?> clazz, final String value) {
		
		if(clazz == Byte.class) {
			return Byte.parseByte(value);
		} else if(clazz == Short.class) {
			return Short.parseShort(value);
		} else if(clazz == Integer.class) {
			return Integer.parseInt(value);
		} else if(clazz == Long.class) {
			return Long.parseLong(value);
		} else if(clazz == Float.class) {
			return Float.parseFloat(value);
		} else if(clazz == Double.class) {
			return Double.parseDouble(value);
		} else if(clazz == Boolean.class) {
			return new Boolean(value.equalsIgnoreCase("true") ||
					value.equalsIgnoreCase("Y"));
		//May want to enforce string length = 1
		} else if(clazz == Character.class) {
			return new Character(value.charAt(0));
		} else {
			throw new MissiveException("Attempted to parse bad class type " + 
					clazz.getCanonicalName());
		}
		
	}
	
}
