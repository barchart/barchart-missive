package com.barchart.missive.api;

import static com.barchart.missive.api.TestRSpec.*;

import com.barchart.missive.api.Raw;

public class TestRefac {

	public static void main(final String[] args) {
		
		final Raw raw = new Raw();
		raw.put(TestRSpec.Account, "Account1");
		raw.put(TestRSpec.FillPrice, 10.0);
		raw.put(TestRSpec.FillQty, 5);
		raw.put(TestRSpec.ExecType, "MARKET");
		
		visit(raw);
		
	}
	
	public static void visit(final Raw raw) {
		
		if(raw.satisfies(ExecRept)) {
			visit(raw.as(new ExecMsv()));
		}
		
		if(raw.satisfies(Fill)) {
			visit(raw.as(new FillMsv()));
		}
		
	}
	
	public static void visit(final FillMsv missive) {
		System.out.println("Visited as Fill");
		System.out.println(missive.toString());
	}
	
	public static void visit(final ExecMsv missive) {
		System.out.println("Visited as ExecRept");
		System.out.println(missive.toString());
	}
	
}
