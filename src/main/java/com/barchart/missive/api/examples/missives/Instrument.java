package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.Tag;

public class Instrument extends FIXMessage {

	public Instrument() {
		include(new Tag<?>[]{
			Symbol,
			Exchange,
			MaturityDate
		});
	}
	
}
