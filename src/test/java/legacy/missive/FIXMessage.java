package legacy.missive;

import static legacy.example.FixSpec.*;
import legacy.OldMissive;

import com.barchart.missive.core.Tag;

public class FIXMessage extends OldMissive {
	
	public FIXMessage() {
		include(new Tag<?>[]{
				MsgSeqNum,
				MsgType,
				SendingTime
		});
	}

}
