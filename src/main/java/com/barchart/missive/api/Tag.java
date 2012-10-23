package com.barchart.missive.api;

public class Tag<V> {

	protected final String name;
	private final Manifest manifest; 
	private final Class<V> clazz;
	
	public Tag(final String name, final Class<V> clazz) {
		this.name = name;
		manifest = new Manifest(name, new Tag<?>[]{this});
		this.clazz = clazz;
		
	}
	
	@SuppressWarnings("unchecked")
	public Tag(final String name, final Manifest manifest) {
		this.name = name;
		this.manifest = manifest;
		clazz = (Class<V>) Missive[].class;
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
	
	public V cast(final Object o) throws MissiveException {
		try {
			return clazz.cast(o);
		} catch(final ClassCastException e) {
			try {
				return clazz.getConstructor(o.getClass()).newInstance(o);
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

}
