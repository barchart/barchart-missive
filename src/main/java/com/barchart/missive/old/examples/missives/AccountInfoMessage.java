package com.barchart.missive.old.examples.missives;

import static com.barchart.missive.old.examples.FixSpec.*;

import com.barchart.missive.Tag;

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
