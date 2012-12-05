package com.barchart.missive.old;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.barchart.missive.MissiveException;
import com.barchart.missive.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class OldMissive {

	// Will replace with hash func and array
	private final Map<Tag<?>, Object> values =
			new HashMap<Tag<?>, Object>();
	
	public OldMissive() {
		
	}
	
	public void include(final Tag<?>[] tags) {
		for(final Tag<?> tag : tags) {
			values.put(tag, null);
		}
	}
	
	public OldMissive(final Tag<?>[] tags) {
		for(final Tag<?> tag : tags) {
			values.put(tag, null);
		}
	}
	
	public Map<Tag<?>, Object> getAll() {
		return Collections.unmodifiableMap(values);
	}
	
	public Set<Tag<?>> getTags() {
		return Collections.unmodifiableSet(values.keySet());
	}
	
	@SuppressWarnings("unchecked")
	public <V> V get(final Tag<V> tag) {
		return (V) values.get(tag);
	}
	
	public <V> void set(final Tag<V> tag, final V v) throws MissiveException {
		if(values.containsKey(tag)) {
			values.put(tag, v);
		} else {
			throw new MissiveException("No such tag " + tag.toString());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copyAll(final OldMissive m) {
		
		for(final Tag t : values.keySet()) {
			if(values.containsKey(t) && m.get(t) != null) {
				values.put(t, m.get(t));
			}
		}
		
	}
	
	public boolean has(final Tag<?> tag) {
		return values.containsKey(tag);
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
	
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
}