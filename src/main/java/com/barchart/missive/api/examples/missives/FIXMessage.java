package com.barchart.missive.api.examples.missives;

import static com.barchart.missive.api.examples.FixSpec.*;

import com.barchart.missive.api.OldMissive;
import com.barchart.missive.api.Tag;

public class FIXMessage extends OldMissive {
	
	public FIXMessage() {
		include(new Tag<?>[]{
				MsgSeqNum,
				MsgType,
				SendingTime
		});
	}

}
