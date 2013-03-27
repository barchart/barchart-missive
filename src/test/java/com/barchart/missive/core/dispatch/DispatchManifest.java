package com.barchart.missive.core.dispatch;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.Manifest;
import com.barchart.missive.core.ObjectMap;

@SuppressWarnings("serial")
public class DispatchManifest extends Manifest<ObjectMap> {
	
	public DispatchManifest() {
		super();
		
		put(Alive.class, new Tag<?>[]{DispatchSpec.ALIVE});
		put(Animal.class, new Tag<?>[]{DispatchSpec.ANIMAL});
		put(Dog.class, new Tag<?>[]{DispatchSpec.DOG});
		put(Rex.class, new Tag<?>[]{DispatchSpec.REX});
		put(Sparky.class, new Tag<?>[]{DispatchSpec.SPARKY});
		
	}

}
