package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.Missive;
import com.barchart.missive.api.Tag;

public class FIXMessage extends Missive {
	
	public FIXMessage() {
		include(new Tag<?>[]{
				MsgSeqNum,
				MsgType,
				SendingTime
		});
	}

}
