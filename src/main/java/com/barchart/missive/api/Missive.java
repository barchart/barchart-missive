package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Missive {

	private final Manafest manafest;
	
	// Will replace with hash func and array
	private final Map<Tag<?>, Object> values =
			new HashMap<Tag<?>, Object>();
	
	public Missive(final Manafest manafest) {
		this.manafest = manafest;
		
		for(final Tag<?> tag : manafest.getTags()) {
			values.put(tag, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <V> V get(final Tag<V> tag) {
		return (V) values.get(tag);
	}
	
	public <V> void set(final V v, final Tag<V> tag) throws MissiveException {
		if(manafest.has(tag)) {
			values.put(tag, v);
		} else {
			throw new MissiveException("No such tag " + tag.toString());
		}
	}
	
	public boolean has(final Tag<?> tag) {
		return values.containsKey(tag);
	}
	
	public Manafest getManafest() {
		return manafest;
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
