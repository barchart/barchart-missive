package com.barchart.missive.api;

import com.barchart.missive.api.Manifest;
import com.barchart.missive.api.Tag;

public final class TestSpec {

	public static final Tag<String> Account = new Tag<String>("Account");
	
	public static final Tag<String> AccountName = new Tag<String>("AccountName");
	public static final Tag<Integer> FCMAccountNumber = new Tag<Integer>("FCMAccountNumber");
	public static final Tag<String> RequestID = new Tag<String>("RequestID"); 
	public static final Tag<Integer> FCMID = new Tag<Integer>("FCMID");
	public static final Tag<String> FCMName = new Tag<String>("FCMName");
	public static final Tag<Boolean> ViewOnly = new Tag<Boolean>("ViewOnly");
	public static final Tag<Boolean> LastFragment = new Tag<Boolean>("LastFragment");
	
	public static final Manifest NoAccountsGroup = 
			new Manifest("NoAccoutnsGroup", new Tag<?>[] {
		Account,
		AccountName,
		FCMAccountNumber,
		FCMID,
		FCMName,
		ViewOnly
	});
	
	public static final Tag<Missive[]> NoAccounts = new Tag<Missive[]>("NoAccounts", NoAccountsGroup);
	
	public static final Manifest AuthorizedAccountsReport = 
			new Manifest("AuthorizedAccountsReport", new Tag<?>[] {
		RequestID,
		LastFragment,
		NoAccounts
	});
	
}
