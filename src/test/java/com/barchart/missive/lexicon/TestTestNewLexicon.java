package com.barchart.missive.lexicon;

import com.barchart.missive.Tag;

public class TestTestNewLexicon extends TestNewLexicon {
	
	public static final Tag<String> AccountName = new Tag<String>("AccountName", String.class);
	public static final Tag<Integer> FCMAccountNumber = new Tag<Integer>("FCMAccountNumber", Integer.class);
	public static final Tag<String> RequestID = new Tag<String>("RequestID", String.class); 
	public static final Tag<Integer> FCMID = new Tag<Integer>("FCMID", Integer.class);
	public static final Tag<String> FCMName = new Tag<String>("FCMName", String.class);
	public static final Tag<Boolean> ViewOnly = new Tag<Boolean>("ViewOnly", Boolean.class);
	public static final Tag<Boolean> LastFragment = new Tag<Boolean>("LastFragment", Boolean.class);
	
	static {
		build();
	}
	
	public static void main(final String[] args) {
		
		System.out.println(TestTestNewLexicon.hasTag(TestNewLexicon.TestByte));
		
	}

}