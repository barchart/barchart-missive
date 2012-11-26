package com.barchart.missive.old.examples.missives;

import static com.barchart.missive.old.examples.FixSpec.*;

import com.barchart.missive.Tag;

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
