package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public class Lexicon {
	
	private final Map<Integer, Tag<?>> toTags;
	private final Map<Tag<?>, Integer> fromTags = 
			new HashMap<Tag<?>, Integer>();
	
	private final Map<String, Manifest> toManifest;
	private final Map<Manifest, String> fromManifest =
			new HashMap<Manifest, String>();
	
	public Lexicon(final Map<Integer, Tag<?>> tags,
			final Map<String, Manifest> manifesto) {
		this.toTags = tags;
		this.toManifest = manifesto;
		
		for(final Entry<Integer, Tag<?>> e : toTags.entrySet()) {
			fromTags.put(e.getValue(), e.getKey());
		}
		
		for(final Entry<String, Manifest> e : toManifest.entrySet()) {
			fromManifest.put(e.getValue(), e.getKey());
		}
		
		if(toTags.size() != fromTags.size() || toManifest.size() != fromManifest.size()) {
			throw new RuntimeException("Maps not 1 to 1 in Lexicon");
		}
		
	}
	
	public String fromManifest(final Manifest manifest) {
		return fromManifest.get(manifest);
	}
	
	public Missive toMissive(final RawData raw) throws MissiveException {

		final Manifest manifest = toManifest.get(raw.name());
		
		if(manifest == null) {
			throw new MissiveException("Unknown manafest: " + raw.name());
		}
		
		return makeInternal(raw, manifest);
		
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private Missive makeInternal(final RawData raw, final Manifest manifest) 
			throws MissiveException {
		
		final Missive m = new Missive(manifest);
		
		for(final Entry<Integer, Object> e : raw.data().entrySet()) {
			final Tag tag = toTags.get(e.getKey());
			
			/* Won't work for primitive arrays */
			if(e.getValue() instanceof RawData[]) {
			
				final RawData[] rawArray = (RawData[]) e.getValue();
				final Missive[] missives = new Missive[rawArray.length];
				
				for(int i = 0; i < rawArray.length; i++) {
					missives[i] = makeInternal(rawArray[i], tag.manifest());
				}
				
				m.set(tag, missives);
				
			} else {
				
				try {
				
					//Temp hack for qfix...
					if(tag.getClazz() == Character.class) {
						m.set(tag, ((String) e.getValue()).charAt(0));
					} else if(tag.getClazz() == Boolean.class){
						if(((String)e.getValue()).equals("Y")) {
							m.set(tag, true);
						} else {
							m.set(tag, false);
						}
					} else {
						m.set(tag, tag.cast(e.getValue()));
					}
				
				} catch (final RuntimeException ex) {
					System.err.println("Threw exception on casting raw object " + tag + 
							":" + e.getValue().toString());
					throw new MissiveException("Threw exception on casting raw object " + tag + 
							":" + e.getValue().toString());
				}
			}
		}
		
		return m;
		
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public RawData fromMissive(final Missive missive) {
		
		final RawData raw = new RawData(fromManifest.get(missive.getManifest()));
		
		for(final Tag tag : missive.getManifest().getTags()) {
			
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