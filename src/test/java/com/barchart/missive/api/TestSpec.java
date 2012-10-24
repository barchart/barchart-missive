package com.barchart.missive.api;

import com.barchart.missive.api.Manifest;
import com.barchart.missive.api.Tag;

/**
 * 
 * @author Gavin M Litchfield
 *
 */
public final class TestSpec {

	public static final Tag<String> Account = new Tag<String>("Account", String.class);
	
	public static final Tag<String> AccountName = new Tag<String>("AccountName", String.class);
	public static final Tag<Integer> FCMAccountNumber = new Tag<Integer>("FCMAccountNumber", Integer.class);
	public static final Tag<String> RequestID = new Tag<String>("RequestID", String.class); 
	public static final Tag<Integer> FCMID = new Tag<Integer>("FCMID", Integer.class);
	public static final Tag<String> FCMName = new Tag<String>("FCMName", String.class);
	public static final Tag<Boolean> ViewOnly = new Tag<Boolean>("ViewOnly", Boolean.class);
	public static final Tag<Boolean> LastFragment = new Tag<Boolean>("LastFragment", Boolean.class);
	
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
