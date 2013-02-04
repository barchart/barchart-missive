/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package example.market;

import com.barchart.missive.core.Tag;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

public interface MarketField {

	Tag<TimeValue> MARKET_TIME = new Tag<TimeValue>() {
	};

	Tag<SizeValue> BOOK_SIZE = Tag.create(SizeValue.class);

}
