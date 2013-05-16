/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.proto_1;

import org.openfeed.proto.data.MarketEntry;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;

/**
 * Tags for fields of {@link MarketEntry}
 */
public interface EntryTag {

	Tag<Long> INDEX = TagFactory.create(Long.class);

	Tag<PriceValue> PRICE = TagFactory.create(PriceValue.class);

	Tag<SizeValue> SIZE = TagFactory.create(SizeValue.class);

	Tag<?>[] ALL = TagFactory.collectTop(EntryTag.class);

}
