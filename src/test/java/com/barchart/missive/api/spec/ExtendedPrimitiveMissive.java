package com.barchart.missive.api.spec;

import static com.barchart.missive.api.spec.TestSpec.EXTEND;

import com.barchart.missive.Tag;

public class ExtendedPrimitiveMissive extends PrimitiveMissive {

	public ExtendedPrimitiveMissive() {
		includeTags(new Tag<?>[]{EXTEND});
	}
	
}
