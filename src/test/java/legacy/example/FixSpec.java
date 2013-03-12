/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package legacy.example;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;

public class FixSpec  {

	/* Header Tags */
	public static final Tag<Integer> MsgSeqNum = TagFactory.create("MsgSeqNum", Integer.class);
	public static final Tag<String> MsgType = TagFactory.create("MsgType", String.class);
	public static final Tag<String> SendingTime = TagFactory.create("SendingTime", String.class);
	
	
	public static final Tag<String> AccountName = TagFactory.create("AccountName", String.class);
	public static final Tag<Integer> AccountID = TagFactory.create("AccountID", Integer.class);
	public static final Tag<Boolean> AccountEnabled = TagFactory.create("AccountEnabled", Boolean.class);
	
	public static final Tag<String> OrderID = TagFactory.create("OrderID", String.class);
	public static final Tag<Character> Side = TagFactory.create("Side", Character.class);
	
	public static final Tag<Double> OrderQty = TagFactory.create("OrderQty", Double.class);
	public static final Tag<Double> OrderPrice = TagFactory.create("OrderPrice", Double.class);
	
	public static final Tag<Integer> FillQty = TagFactory.create("FillQty", Integer.class);
	public static final Tag<Double> FillPrice = TagFactory.create("FillPrice", Double.class);
	
	public static final Tag<String> Symbol = TagFactory.create("Symbol", String.class);
	public static final Tag<String> Exchange = TagFactory.create("Exchange", String.class);
	public static final Tag<String> MaturityDate = TagFactory.create("MaturityDate", String.class);

	enum OrderType {
		LIMIT, MARKET
	}
	
	public static final Tag<OrderType> OrdType = TagFactory.create("OrderType", OrderType.class);
	
	static {
		
	}
	
}
