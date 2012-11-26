package com.barchart.missive.old;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class Raw {

	private final Map<Tag<?>, Object> values =
			new HashMap<Tag<?>, Object>();
	
	public Raw() {
		
	}
	
	public Raw(final OldMissive m) {
		for(final Entry<Tag<?>, Object> e : m.getAll().entrySet()) {
			if(e.getValue() != null) {
				values.put(e.getKey(), e.getValue());
			}
		}
	}
	
	public <V> void put(final Tag<V> tag, final V value) {
		values.put(tag, tag.cast(value));
	}
	
	@SuppressWarnings("unchecked")
	public <V> V get(final Tag<V> tag) {
		return (V) values.get(tag);
	}
	
	public Set<Entry<Tag<?>, Object>> data() {
		return values.entrySet();
	}
	
	public Map<Tag<?>, Object> map() {
		return values;
	}
	
	public int size() {
		return values.size();
	}
	
	public boolean satisfies(final OldMissive m) {
		
		for(final Tag<?> tag : m.getTags()) {
			if(!values.containsKey(tag)) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <M extends OldMissive> M as(final M missive) {
		
		for(final Tag tag : missive.getTags()) {
			missive.set(tag, values.get(tag));
		}
		return missive;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		
		for(final Entry<Tag<?>, Object> e : values.entrySet()) {
			sb.append(e.getKey().getName() + ":" + e.getValue().toString() + "\n");
		}
		
		return sb.toString();
	}
	
}
