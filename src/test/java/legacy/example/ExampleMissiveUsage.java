/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package legacy.example;

import static legacy.example.FixSpec.*;
import legacy.OldMissive;
import legacy.example.FixSpec.OrderType;
import legacy.missive.AccountInfoMessage;
import legacy.missive.FIXMessage;
import legacy.missive.Fill;
import legacy.missive.Instrument;
import legacy.missive.OrderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.missive.core.Tag;

public class ExampleMissiveUsage {

	protected static final Logger log = LoggerFactory
			.getLogger(ExampleMissiveUsage.class);

	public static void main(final String[] args) {

		/* Typesafety examples */
		final OldMissive example = new OldMissive(new Tag<?>[] { MsgSeqNum,
				MsgType, AccountEnabled, Side, FillPrice, OrdType });

		example.set(MsgSeqNum, 111);
		example.set(MsgType, "Example");
		example.set(AccountEnabled, true);
		example.set(Side, 'B');
		example.set(FillPrice, 99.95);
		example.set(OrdType, OrderType.LIMIT);

		final int seqNum = example.get(MsgSeqNum);
		final String msgtype = example.get(MsgType);
		final boolean enabled = example.get(AccountEnabled);
		final char side = example.get(Side);
		final double price = example.get(FillPrice);
		final OrderType type = example.get(OrdType);

		/* Tag casting examples */
		example.set(MsgSeqNum, MsgSeqNum.cast("222"));
		System.out
				.println("222 parsed to " + example.get(MsgSeqNum).toString());

		example.set(AccountEnabled, AccountEnabled.cast("false"));
		log.info("false parsed to " + example.get(AccountEnabled).toString());

		example.set(Side, Side.cast("A"));
		log.info("A parsed to " + example.get(Side).toString());

		example.set(OrdType, OrdType.cast("LIMIT"));
		System.out
				.println("LIMIT parsed to " + example.get(OrdType).toString());

		log.info("");

		/* Raw and Missive type examples */
		// Raw raw = new Raw();
		//
		// raw.put(MsgSeqNum, 111);
		// raw.put(MsgType, "Example");
		// raw.put(SendingTime, "Now");
		// raw.put(AccountName, "SuperTrader");
		// raw.put(AccountID, 1100110011);
		// raw.put(AccountEnabled, true);
		// raw.put(OrderID, "Order1");
		// raw.put(Side, 'B');
		// raw.put(OrderQty, 10.0);
		// raw.put(OrderPrice, 99.95);
		// raw.put(FillQty, 10);
		// raw.put(FillPrice, 99.89);
		// raw.put(Symbol, "GOOG");
		// raw.put(Exchange, "CME");
		// raw.put(MaturityDate, "December");
		// raw.put(OrdType, OrderType.LIMIT);
		//
		// FIXMessage fix = new FIXMessage();
		// if(raw.satisfies(fix)) {
		// updateMsgSeqTracker(raw.as(fix));
		// }
		//
		// AccountInfoMessage account = new AccountInfoMessage();
		// if(raw.satisfies(account)) {
		// storeAccountInfo(raw.as(account));
		// }
		//
		// OrderRequest order = new OrderRequest();
		// if(raw.satisfies(order)) {
		// updateOrderHistory(raw.as(order));
		// }
		//
		// Instrument inst = new Instrument();
		// if(raw.satisfies(inst)) {
		// storeInstrumentInfo(raw.as(inst));
		// }
		//
		// Fill fill = new Fill();
		// if(raw.satisfies(fill)) {
		// processFill(raw.as(fill));
		// }

	}

	public static void updateMsgSeqTracker(final FIXMessage fix) {
		log.info("Handled raw as fix message");
	}

	public static void storeAccountInfo(final AccountInfoMessage account) {
		log.info("Handled raw as account info message");
	}

	public static void updateOrderHistory(final OrderRequest order) {
		log.info("Handled raw as order request");
	}

	public static void storeInstrumentInfo(final Instrument inst) {
		log.info("Handled raw as instrument def");
	}

	public static void processFill(final Fill fill) {
		log.info("Handled raw as fill message");
	}

}
