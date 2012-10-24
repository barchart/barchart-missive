package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class Missive {

	private final Manifest manifest;
	
	// Will replace with hash func and array
	private final Map<Tag<?>, Object> values =
			new HashMap<Tag<?>, Object>();
	
	public Missive(final Manifest manifest) {
		this.manifest = manifest;
		
		for(final Tag<?> tag : manifest.getTags()) {
			values.put(tag, null);
		}
	}
	
	public Missive(final Manifest...manifests) {
		manifest = new Manifest(manifests);
		
		for(final Tag<?> tag : manifest.getTags()) {
			values.put(tag, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <V> V get(final Tag<V> tag) {
		return (V) values.get(tag);
	}
	
	public <V> void set(final V v, final Tag<V> tag) throws MissiveException {
		if(manifest.has(tag)) {
			values.put(tag, v);
		} else {
			throw new MissiveException("No such tag " + tag.toString());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copyAll(final Missive m) {
		
		for(final Tag t : m.getManifest().getTags()) {
			if(values.containsKey(t) && m.get(t) != null) {
				values.put(t, m.get(t));
			}
		}
		
	}
	
	public boolean has(final Tag<?> tag) {
		return values.containsKey(tag);
	}
	
	public Manifest getManifest() {
		return manifest;
	}
	
	@Override
	public String toString() {
		
		final StringBuffer sb = new StringBuffer();
		
		for(final Entry<Tag<?>, Object> e : values.entrySet()) {
			if(e.getValue() == null) {
				sb.append(e.getKey().toString() + ":NULL\n");
			} else {
				if(e.getValue() instanceof Object[]) {
					sb.append(e.getKey().toString() + ":\n");
					for(final Object o : (Object[]) e.getValue()) {
						sb.append("\t" + o.toString() +"\n");
					}
				} else {
					sb.append(e.getKey().toString() + ":" + e.getValue().toString() + "\n");
				}
			}
		}
		
		return sb.toString();
		
	}
	
}
