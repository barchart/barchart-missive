package legacy.missive;

import static legacy.example.FixSpec.*;

import com.barchart.missive.core.Tag;

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
