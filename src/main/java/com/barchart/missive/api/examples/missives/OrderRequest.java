package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.Tag;

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
