package com.barchart.missive.spec;

import static com.barchart.missive.TestSpec.BOOLEAN1;
import static com.barchart.missive.TestSpec.BOOLEAN2;
import static com.barchart.missive.TestSpec.BOOLEAN3;
import static com.barchart.missive.TestSpec.BYTE;
import static com.barchart.missive.TestSpec.CHARACTER;
import static com.barchart.missive.TestSpec.DOUBLE;
import static com.barchart.missive.TestSpec.FLOAT;
import static com.barchart.missive.TestSpec.INTEGER;
import static com.barchart.missive.TestSpec.LONG;
import static com.barchart.missive.TestSpec.SHORT;

import com.barchart.missive.Tag;
import com.barchart.missive.fast.FastMissive;

public class PrimitiveMissive extends FastMissive {
	
	public PrimitiveMissive() {
		includeTags(new Tag<?>[]{
				BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, 
				BOOLEAN1, BOOLEAN2, BOOLEAN3, CHARACTER});
	}

}
