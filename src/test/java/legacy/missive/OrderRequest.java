package legacy.missive;

import static legacy.example.FixSpec.*;

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
