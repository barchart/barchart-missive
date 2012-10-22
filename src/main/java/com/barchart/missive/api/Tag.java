package com.barchart.missive.api;

public class Tag<V> {

	protected final String name;
	private final Manifest manafest; 
	
	public Tag(final String name) {
		this.name = name;
		manafest = new Manifest(name, new Tag<?>[]{this});
	}
	
	public Tag(final String name, final Manifest manafest) {
		this.name = name;
		this.manafest = manafest;
	}

	public final String getName() {
		return name;
	}
	
	public Manifest manafest() {
		return manafest;
	}
	
	@SuppressWarnings("unchecked")
	public V cast(final Object o) throws MissiveException {
		try {
			return (V) o;
		} catch(final ClassCastException e) {
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

}
