package com.barchart.missive.core.dispatch;

import com.barchart.missive.core.Dispatch;
import com.barchart.missive.core.DispatchService;
import com.barchart.missive.core.ObjectMap;

public class DispatchExample {
	
	private final DispatchService dispatcher;
	
	public DispatchExample() {
		dispatcher = new DispatchService(this);
	}
	
	public void handle(final ObjectMap map) {
		dispatcher.dispatch(map);
	}
	
	@Dispatch
	@SuppressWarnings("unused")
	private void dispatch(final Alive alive) {
		alive.set(DispatchSpec.ALIVE, "ALIVE");
	}
	
	@Dispatch
	@SuppressWarnings("unused")
	private void dispatch(final Animal animal) {
		animal.set(DispatchSpec.ANIMAL, "ANIMAL");
	}
	
	@Dispatch
	@SuppressWarnings("unused")
	private void dispatch(final Dog dog) {
		dog.set(DispatchSpec.DOG, "DOG");
	}
	
	@Dispatch
	@SuppressWarnings("unused")
	private void dispatch(final Rex rex) {
		rex.set(DispatchSpec.REX, "REX");
	}
	
	@Dispatch
	@SuppressWarnings("unused")
	private void dispatch(final Sparky sparky) {
		sparky.set(DispatchSpec.SPARKY, "SPARKY");
	}
	
}
