package com.barchart.missive.refactoring;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.barchart.missive.api.MissiveException;
import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class Missive implements TagMap {
	
	// Will replace with hash func and array
	protected final Map<Tag<?>, Object> values =
		new HashMap<Tag<?>, Object>();

	public Missive() {
		
	}
	
	public Missive(final Tag<?>[] tags) {
		for(final Tag<?> tag :tags) {
			put(tag, null);
		}
	}
	
	public boolean isSubsetOf(final Missive m) {
		
		for(final Tag<?> tag : m.getTags()) {
			if(!containsTag(tag)) {
				return false;
			}
		}
		
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends Missive> M cast(final M m) {
		for(final Tag tag : m.getTags()) {
			m.set(tag, values.get(tag));
		}
		
		return m;
	}
	
	protected void includeTags(final Tag<?>[] tags) {
		for(final Tag<?> tag : tags) {
			put(tag,  null);
		}
	}
	
	protected <V> void set(final Tag<V> tag, final V value) {
		if(values.containsKey(tag)) {
			put(tag, value);
		} else {
			throw new MissiveException("No such tag " + tag.toString());
		}
	}
	
	protected <V> void put(final Tag<V> tag, final V value) {
		values.put(tag, value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V get(Tag<V> tag) {
		return (V) values.get(tag);
	}
	
	@Override
	public Tag<?>[] getTags() {
		return values.keySet().toArray(new Tag<?>[0]);
	}

	@Override
	public boolean containsTag(Tag<?> tag) {
		return values.containsKey(tag);
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
		
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
