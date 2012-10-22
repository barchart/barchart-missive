package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Lexicon {
	
	private final Map<Integer, Tag<?>> toTags;
	private final Map<Tag<?>, Integer> fromTags = 
			new HashMap<Tag<?>, Integer>();
	
	private final Map<String, Manifest> toManafest;
	private final Map<Manifest, String> fromManafest =
			new HashMap<Manifest, String>();
	
	public Lexicon(final Map<Integer, Tag<?>> tags,
			final Map<String, Manifest> manafesto) {
		this.toTags = tags;
		this.toManafest = manafesto;
		
		for(final Entry<Integer, Tag<?>> e : toTags.entrySet()) {
			fromTags.put(e.getValue(), e.getKey());
		}
		
		for(final Entry<String, Manifest> e : toManafest.entrySet()) {
			fromManafest.put(e.getValue(), e.getKey());
		}
		
		if(toTags.size() != fromTags.size() || toManafest.size() != fromManafest.size()) {
			throw new RuntimeException("Maps not 1 to 1 in Lexicon");
		}
		
	}
	
	public String fromManafest(final Manifest manafest) {
		return fromManafest.get(manafest);
	}
	
	public Missive toMissive(final RawData raw) throws MissiveException {

		final Manifest manafest = toManafest.get(raw.name());
		
		if(manafest == null) {
			throw new MissiveException("Unknown manafest: " + raw.name());
		}
		
		return makeInternal(raw, manafest);
		
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private Missive makeInternal(final RawData raw, final Manifest manafest) 
			throws MissiveException {
		
		final Missive m = new Missive(manafest);
		
		for(final Entry<Integer, Object> e : raw.data().entrySet()) {
			final Tag tag = toTags.get(e.getKey());
			
			/* Won't work for primitive arrays */
			if(e.getValue() instanceof RawData[]) {
			
				final RawData[] rawArray = (RawData[]) e.getValue();
				final Missive[] missives = new Missive[rawArray.length];
				
				for(int i = 0; i < rawArray.length; i++) {
					missives[i] = makeInternal(rawArray[i], tag.manafest());
				}
				
				m.set(missives, tag);
			} else {
				m.set(tag.cast(e.getValue()), tag);
			}
		}
		
		return m;
		
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public RawData fromMissive(final Missive missive) {
		
		final RawData raw = new RawData(fromManafest.get(missive.getManafest()));
		
		for(final Tag tag : missive.getManafest().getTags()) {
			
			if(missive.get(tag) instanceof Missive[]) {
				
				final Missive[] groups = (Missive[]) missive.get(tag);
				final RawData[] rawG = new RawData[groups.length];
				
				int counter = 0;
				for(final Missive m : groups) {
					rawG[counter] = fromMissive(m);
					counter++;
				}
				
				raw.put(fromTags.get(tag), rawG);
				
			} else {
				raw.put(fromTags.get(tag), missive.get(tag));
			}
			
		}
		
		return raw;
		
	}
	
}