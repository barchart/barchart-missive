package com.barchart.missive.old.examples.missives;

import static com.barchart.missive.old.examples.FixSpec.*;

import com.barchart.missive.core.Tag;

public class OrderRequest extends FIXMessage {

	public OrderRequest() {
		include(new Tag<?>[]{
				AccountID,
				OrderID,
				Symbol,
				Side,
				OrderQty,
				OrderPrice,
				OrdType
		});
		
		
	}
	
}
