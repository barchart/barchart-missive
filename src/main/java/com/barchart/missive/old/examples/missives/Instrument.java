package com.barchart.missive.old.examples.missives;

import static com.barchart.missive.old.examples.FixSpec.*;

import com.barchart.missive.core.Tag;

public class Instrument extends FIXMessage {

	public Instrument() {
		include(new Tag<?>[]{
			Symbol,
			Exchange,
			MaturityDate
		});
	}
	
}
