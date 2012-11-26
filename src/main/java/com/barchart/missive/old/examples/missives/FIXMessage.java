package com.barchart.missive.old.examples.missives;

import static com.barchart.missive.old.examples.FixSpec.*;

import com.barchart.missive.Tag;
import com.barchart.missive.old.OldMissive;

public class FIXMessage extends OldMissive {
	
	public FIXMessage() {
		include(new Tag<?>[]{
				MsgSeqNum,
				MsgType,
				SendingTime
		});
	}

}
