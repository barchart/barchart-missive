package com.barchart.missive.api;

/**
 * 
 * @author Gavin M Litchfield
 *
 * @param <V>
 */
public class Tag<V> {
	
	protected final String name;
	private final Manifest manifest; 
	private final Class<V> clazz;
	private final boolean isPrim;
	private final boolean isComplex;
	
	public Tag(final String name, final Class<V> clazz) {
		this.name = name;
		manifest = new Manifest(name, new Tag<?>[]{this});
		this.clazz = clazz;
		isPrim = clazz.isPrimitive();
		
		if(clazz.isAssignableFrom(Missive[].class)) {
			isComplex = true;
		} else {
			isComplex = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Tag(final String name, final Manifest manifest) {
		this.name = name;
		this.manifest = manifest;
		clazz = (Class<V>) Missive[].class;
		isPrim = false;
		isComplex = true;
	}

	public final String getName() {
		return name;
	}
	
	public Class<V> getClazz() {
		return clazz;
	}
	
	public Manifest manifest() {
		return manifest;
	}
	
	public final boolean isPrimitive() {
		return isPrim;
	}
	
	public final boolean isComplex() {
		return isComplex;
	}
	
	@SuppressWarnings("unchecked")
	public V cast(final Object o) throws MissiveException {
		try {
			return clazz.cast(o);
		} catch(final ClassCastException e) {
			try {
				if(isPrim && o instanceof String) {
					return (V) parsePrimitiveFromString(clazz, (String) o);
				} else {
					return clazz.getConstructor(o.getClass()).newInstance(o);
				}
			} catch (final Exception e1) {
				e1.printStackTrace();
				throw new MissiveException("Failed to cast object in tag " + name);
			} 
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
			if(value.equals("true") ||
					value.equals("Y")) {
				return new Boolean(true);
			} else {
				return new Boolean(false);
			}
		//May want to enforce string length = 1
		} else if(clazz == Character.class) {
			return new Character(value.charAt(0));
		} else {
			throw new MissiveException("Attempted to parse bad class type " + 
					clazz.getCanonicalName());
		}
		
	}
	
}
