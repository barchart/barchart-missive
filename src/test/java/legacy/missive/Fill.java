package legacy.missive;

import static legacy.example.FixSpec.*;

import com.barchart.missive.core.Tag;

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
