/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.specs;

import com.barchart.missive.core.Tag;

public interface Tags {

	enum E {
		ENUM1, ENUM2, ENUM3, ENUM4, ENUM5
	}

	Tag<Integer> INT1 = Tag.create("INT1", Integer.class);
	Tag<Integer> INT2 = Tag.create("INT2", Integer.class);
	Tag<Integer> INT3 = Tag.create("INT3", Integer.class);
	Tag<Integer> INT4 = Tag.create("INT4", Integer.class);
	Tag<Integer> INT5 = Tag.create("INT5", Integer.class);
	Tag<Integer> INT6 = Tag.create("INT6", Integer.class);
	Tag<Integer> INT7 = Tag.create("INT7", Integer.class);

	Tag<E> ENUM1 = Tag.create("ENUM1", E.class);
	Tag<E> ENUM2 = Tag.create("ENUM2", E.class);
	Tag<E> ENUM3 = Tag.create("ENUM3", E.class);
	Tag<E> ENUM4 = Tag.create("ENUM4", E.class);
	Tag<E> ENUM5 = Tag.create("ENUM5", E.class);

	Tag<Double> DOUBLE1 = Tag.create("DOUBLE1", Double.class);
	Tag<Double> DOUBLE2 = Tag.create("DOUBLE2", Double.class);
	Tag<Double> DOUBLE3 = Tag.create("DOUBLE3", Double.class);
	Tag<Double> DOUBLE4 = Tag.create("DOUBLE4", Double.class);
	Tag<Double> DOUBLE5 = Tag.create("DOUBLE5", Double.class);
	Tag<Double> DOUBLE6 = Tag.create("DOUBLE6", Double.class);
	Tag<Double> DOUBLE7 = Tag.create("DOUBLE7", Double.class);

	Tag<String> STRING1 = Tag.create("STRING1", String.class);
	Tag<String> STRING2 = Tag.create("STRING2", String.class);
	Tag<String> STRING3 = Tag.create("STRING3", String.class);
	Tag<String> STRING4 = Tag.create("STRING4", String.class);
	Tag<String> STRING5 = Tag.create("STRING5", String.class);
	Tag<String> STRING6 = Tag.create("STRING6", String.class);
	Tag<String> STRING7 = Tag.create("STRING7", String.class);

}
