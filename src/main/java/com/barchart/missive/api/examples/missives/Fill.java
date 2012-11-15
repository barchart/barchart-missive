package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.Tag;

public class Fill extends FIXMessage {

	public Fill() {
		include(new Tag<?>[]{
				AccountID,
				OrderID,
				Symbol,
				Exchange,
				Side,
				FillQty,
				FillPrice
		});
	}
	
}
