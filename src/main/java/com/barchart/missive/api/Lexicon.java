package com.barchart.missive.api;

import java.util.Map;
import java.util.Map.Entry;

public class Lexicon {
	
	private final Map<Integer, Tag<?>> tags;
	private final Map<String, Manafest> manafesto;
	
	public Lexicon(final Map<Integer, Tag<?>> tags,
			final Map<String, Manafest> manafesto) {
		this.tags = tags;
		this.manafesto = manafesto;
	}
	
	public Missive make(final RawData raw) throws MissiveException {

		final Manafest manafest = manafesto.get(raw.name());
		
		if(manafest == null) {
			throw new MissiveException("Unknown manafest: " + raw.name());
		}
		
		return makeInternal(raw, manafest);
		
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	private Missive makeInternal(final RawData raw, final Manafest manafest) throws MissiveException {
		
		final Missive m = new Missive(manafest);
		
		for(final Entry<Integer, Object> e : raw.data().entrySet()) {
			final Tag tag = tags.get(e.getKey());
			
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
	
}
