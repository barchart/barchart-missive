package com.barchart.missive.spec;

import static com.barchart.missive.TestSpec.EXTEND;

import com.barchart.missive.Tag;

public class ExtendedPrimitiveMissive extends PrimitiveMissive {

	public ExtendedPrimitiveMissive() {
		includeTags(new Tag<?>[]{EXTEND});
	}
	
}
