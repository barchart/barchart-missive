/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
