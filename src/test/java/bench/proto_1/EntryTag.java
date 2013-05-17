/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.proto_1;

import org.openfeed.proto.data.MarketEntry;
import org.openfeed.proto.data.MarketEntry.*;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;

/**
 * Tags for fields of {@link MarketEntry}
 */
public interface EntryTag {

	Tag<Action> ACTION = TagFactory.create(Action.class);
	
	Tag<Type> TYPE = TagFactory.create(Type.class);
	
	Tag<Descriptor> DESCRIPTOR = TagFactory.create(Descriptor.class);
	
	
	Tag<Long> MARKET_ID = TagFactory.create(Long.class);
	
	Tag<Long> INDEX = TagFactory.create(Long.class);
	
	//Timestamp, trade date

	Tag<PriceValue> PRICE = TagFactory.create(PriceValue.class);

	Tag<SizeValue> SIZE = TagFactory.create(SizeValue.class);

	Tag<?>[] ALL = TagFactory.collectTop(EntryTag.class);

}
