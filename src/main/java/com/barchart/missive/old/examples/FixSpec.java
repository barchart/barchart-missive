package com.barchart.missive.old.examples;

import com.barchart.missive.core.NewLexicon;
import com.barchart.missive.core.Tag;

public class FixSpec extends NewLexicon {

	/* Header Tags */
	public static final Tag<Integer> MsgSeqNum = new Tag<Integer>("MsgSeqNum", Integer.class);
	public static final Tag<String> MsgType = new Tag<String>("MsgType", String.class);
	public static final Tag<String> SendingTime = new Tag<String>("SendingTime", String.class);
	
	
	public static final Tag<String> AccountName = new Tag<String>("AccountName", String.class);
	public static final Tag<Integer> AccountID = new Tag<Integer>("AccountID", Integer.class);
	public static final Tag<Boolean> AccountEnabled = new Tag<Boolean>("AccountEnabled", Boolean.class);
	
	public static final Tag<String> OrderID = new Tag<String>("OrderID", String.class);
	public static final Tag<Character> Side = new Tag<Character>("Side", Character.class);
	
	public static final Tag<Double> OrderQty = new Tag<Double>("OrderQty", Double.class);
	public static final Tag<Double> OrderPrice = new Tag<Double>("OrderPrice", Double.class);
	
	public static final Tag<Integer> FillQty = new Tag<Integer>("FillQty", Integer.class);
	public static final Tag<Double> FillPrice = new Tag<Double>("FillPrice", Double.class);
	
	public static final Tag<String> Symbol = new Tag<String>("Symbol", String.class);
	public static final Tag<String> Exchange = new Tag<String>("Exchange", String.class);
	public static final Tag<String> MaturityDate = new Tag<String>("MaturityDate", String.class);

	enum OrderType {
		LIMIT, MARKET
	}
	
	public static final Tag<OrderType> OrdType = new Tag<OrderType>("OrderType", OrderType.class);
	
	static {
		build();
	}
	
}
