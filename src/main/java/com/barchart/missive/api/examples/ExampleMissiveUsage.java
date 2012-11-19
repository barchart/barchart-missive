package com.barchart.missive.api.examples;

import static com.barchart.missive.api.examples.FixSpec.*;
import static com.barchart.missive.api.examples.FixSpec.FillPrice;
import static com.barchart.missive.api.examples.FixSpec.MsgSeqNum;
import static com.barchart.missive.api.examples.FixSpec.MsgType;
import static com.barchart.missive.api.examples.FixSpec.OrdType;
import static com.barchart.missive.api.examples.FixSpec.Side;

import com.barchart.missive.api.OldMissive;
import com.barchart.missive.api.Raw;
import com.barchart.missive.api.Tag;
import com.barchart.missive.api.examples.FixSpec.OrderType;
import com.barchart.missive.api.examples.missives.AccountInfoMessage;
import com.barchart.missive.api.examples.missives.FIXMessage;
import com.barchart.missive.api.examples.missives.Fill;
import com.barchart.missive.api.examples.missives.Instrument;
import com.barchart.missive.api.examples.missives.OrderRequest;

public class ExampleMissiveUsage {

	public static void main(final String[] args) {
		
		/* Typesafety examples */
		OldMissive example = new OldMissive(new Tag<?>[]{
				MsgSeqNum,
				MsgType,
				AccountEnabled,
				Side,
				FillPrice,
				OrdType
			});
		
		example.set(MsgSeqNum, 111);
		example.set(MsgType, "Example");
		example.set(AccountEnabled, true);
		example.set(Side, 'B');
		example.set(FillPrice, 99.95);
		example.set(OrdType, OrderType.LIMIT);
		
		int seqNum = example.get(MsgSeqNum);
		String msgtype = example.get(MsgType);
		boolean enabled = example.get(AccountEnabled);
		char side = example.get(Side);
		double price = example.get(FillPrice);
		OrderType type = example.get(OrdType);
		
		/* Tag casting examples */
		example.set(MsgSeqNum, MsgSeqNum.cast("222"));
		System.out.println("222 parsed to " + example.get(MsgSeqNum).toString());
		
		example.set(AccountEnabled, AccountEnabled.cast("false"));
		System.out.println("false parsed to " + example.get(AccountEnabled).toString());
		
		example.set(Side, Side.cast("A"));
		System.out.println("A parsed to " + example.get(Side).toString());
		
		example.set(OrdType, OrdType.cast("LIMIT"));
		System.out.println("LIMIT parsed to " + example.get(OrdType).toString());
		
		System.out.println();
		
		/* Raw and Missive type examples */
		Raw raw = new Raw();
		
		raw.put(MsgSeqNum, 111);
		raw.put(MsgType, "Example");
		raw.put(SendingTime, "Now");
		raw.put(AccountName, "SuperTrader");
		raw.put(AccountID, 1100110011);
		raw.put(AccountEnabled, true);
		raw.put(OrderID, "Order1");
		raw.put(Side, 'B');
		raw.put(OrderQty, 10.0);
		raw.put(OrderPrice, 99.95);
		raw.put(FillQty, 10);
		raw.put(FillPrice, 99.89);
		raw.put(Symbol, "GOOG");
		raw.put(Exchange, "CME");
		raw.put(MaturityDate, "December");
		raw.put(OrdType, OrderType.LIMIT);
		
		FIXMessage fix = new FIXMessage();
		if(raw.satisfies(fix)) {
			updateMsgSeqTracker(raw.as(fix));
		}
		
		AccountInfoMessage account = new AccountInfoMessage();
		if(raw.satisfies(account)) {
			storeAccountInfo(raw.as(account));
		}
		
		OrderRequest order = new OrderRequest();
		if(raw.satisfies(order)) {
			updateOrderHistory(raw.as(order));
		}
		
		Instrument inst = new Instrument();
		if(raw.satisfies(inst)) {
			storeInstrumentInfo(raw.as(inst));
		}
		
		Fill fill = new Fill();
		if(raw.satisfies(fill)) {
			processFill(raw.as(fill));
		}
		
	}
	
	public static void updateMsgSeqTracker(final FIXMessage fix) {
		System.out.println("Handled raw as fix message");
	}
	
	public static void storeAccountInfo(final AccountInfoMessage account) {
		System.out.println("Handled raw as account info message");
	}
	
	public static void updateOrderHistory(final OrderRequest order) {
		System.out.println("Handled raw as order request");
	}
	
	public static void storeInstrumentInfo(final Instrument inst) {
		System.out.println("Handled raw as instrument def");
	}
	
	public static void processFill(final Fill fill) {
		System.out.println("Handled raw as fill message");
	}
	
}
