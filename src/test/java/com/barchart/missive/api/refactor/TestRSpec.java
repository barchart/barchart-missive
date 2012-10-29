package com.barchart.missive.api.refactor;

import com.barchart.missive.api.Manifest;
import com.barchart.missive.api.Tag;

public class TestRSpec {

	public static final Tag<String> Account = new Tag<String>("Account", String.class);
	public static final Tag<Double> FillPrice = new Tag<Double>("FillPrice", Double.class);
	public static final Tag<Integer> FillQty = new Tag<Integer>("FillQty", Integer.class);
	
	public static final Tag<String> ExecType = new Tag<String>("ExecType", String.class);
	
	public static final Manifest Fill = new Manifest("Fill", new Tag<?>[] {
		Account, FillPrice, FillQty
	});
	
	public static final Manifest ExecRept = new Manifest("ExecRept", new Tag<?>[] {
		Account, ExecType	
	});
	
}
