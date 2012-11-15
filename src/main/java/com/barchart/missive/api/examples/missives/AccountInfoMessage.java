package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.Tag;

public class AccountInfoMessage extends FIXMessage {

	public AccountInfoMessage() {
		include(new Tag<?>[]{
			AccountName,
			AccountID,
			AccountEnabled
		});
		
		set(MsgType, "AccountInfo");
		
	}
	
}
