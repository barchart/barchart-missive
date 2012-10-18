package com.barchart.missive.api;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static final Map<Integer, Tag<?>> tags = 
			new HashMap<Integer, Tag<?>>();
	
	public static final Map<String, Manafest> manafesto = 
			new HashMap<String, Manafest>();
	
	static {
		
		tags.put(1, TestSpec.Account);
		tags.put(20010, TestSpec.AccountName);
		tags.put(20034, TestSpec.RequestID);
		tags.put(20014, TestSpec.FCMAccountNumber);
		tags.put(20053, TestSpec.FCMID);
		tags.put(20059, TestSpec.FCMName);
		tags.put(20060, TestSpec.ViewOnly);
		tags.put(50893, TestSpec.LastFragment);
		
		tags.put(20058, TestSpec.NoAccounts);
		
		/* External Manafest Namespace */
		manafesto.put("UZR", TestSpec.AuthorizedAccountsReport);
		
	}
	
	public static Lexicon LEXI = new Lexicon(tags, manafesto);
	
	public static void main(final String[] args) throws MissiveException {
	
		
		
		final Missive m = new Missive(TestSpec.AuthorizedAccountsReport);
		
		m.set("TestAccountReport", TestSpec.RequestID);
		m.set(true, TestSpec.LastFragment);
		
		final Missive group = new Missive(TestSpec.NoAccountsGroup);
		
		group.set("Account1", TestSpec.Account);
		group.set("AccountName1", TestSpec.AccountName);
		group.set(1000, TestSpec.FCMAccountNumber);
		group.set(1001, TestSpec.FCMID);
		group.set("FCMName1", TestSpec.FCMName);
		group.set(false, TestSpec.ViewOnly);
		
		final Missive[] groups = new Missive[]{group};
		
		m.set(groups, TestSpec.NoAccounts);
		
		System.out.println(m.toString());
		
		/* Test Raw to Missive */
		
		final Map<Integer, Object> rawGMap1 = new HashMap<Integer, Object>();
		rawGMap1.put(1, "Account1");
		rawGMap1.put(20010, "AccountName1");
		rawGMap1.put(20014, 10001);
		rawGMap1.put(20053, 50001);
		rawGMap1.put(20059, "FCMName1");
		rawGMap1.put(20060, false);
		
		final Map<Integer, Object> rawGMap2 = new HashMap<Integer, Object>();
		rawGMap2.put(1, "Account2");
		rawGMap2.put(20010, "AccountName2");
		rawGMap2.put(20014, 10002);
		rawGMap2.put(20053, 50002);
		rawGMap2.put(20059, "FCMName2");
		rawGMap2.put(20060, true);
		
		final RawData[] rawGroups = new RawData[2];
		rawGroups[0] = new RawData("Unknown", rawGMap1);
		rawGroups[1] = new RawData("Unknown", rawGMap2);
		
		final Map<Integer, Object> rawMap = new HashMap<Integer, Object>();
		
		rawMap.put(20034, "TestRequestID1");
		rawMap.put(50893, true);
		rawMap.put(20058, rawGroups);
		
		final RawData rawData = new RawData("UZR", rawMap);
		
		final Missive fromRaw = LEXI.toMissive(rawData);
		
		System.out.println(fromRaw.toString());
		
	}
	
}
