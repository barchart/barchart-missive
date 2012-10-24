package com.barchart.missive.api;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class Manifest  {

	private final String name;
	private final Tag<?>[] tags;
	
	public Manifest(final String name, final Tag<?>[] value) {
		this.name = name;
		this.tags = value;
	}
	
	public Manifest(final String name, final Tag<?>[]...values) {
		this.name = name;
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();
		for(final Tag<?>[] tagArray : values) {
			for(final Tag<?> tag : tagArray) {
				tagSet.add(tag);
			}
		}
		tags = (Tag<?>[]) tagSet.toArray();
	}
	
	public Manifest(final Manifest...manifests) {
		final Set<Tag<?>> tagSet = new HashSet<Tag<?>>();
		final Set<String> nameSet = new TreeSet<String>();
		
		for(final Manifest m : manifests) {
			nameSet.add(m.name());
			for(final Tag<?> tag : m.getTags()) {
				tagSet.add(tag);
			}
		}
		
		final StringBuffer sb = new StringBuffer();
		for(final String name : nameSet) {
			sb.append(name + "&");
		}
		name = sb.substring(0, sb.length()-1);
		tags = (Tag<?>[]) tagSet.toArray();
	}
	
	public Tag<?>[] getTags() {
		return tags;
	}
	
	public boolean has(final Tag<?> tag) {
		for(final Tag<?> t : tags) {
			if(t.hashCode() == tag.hashCode()) {
				return true;
			}
		}
		return false;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		int code = name.hashCode();
		
		for(final Tag<?> tag : tags) {
			code = code * tag.hashCode();
		}
		
		return code;
	}
	
}
