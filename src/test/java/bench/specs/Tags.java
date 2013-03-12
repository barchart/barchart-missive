/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.specs;

import com.barchart.missive.api.Tag;
import com.barchart.missive.core.TagFactory;

public interface Tags {

	enum E {
		ENUM1, ENUM2, ENUM3, ENUM4, ENUM5
	}

	Tag<Integer> INT1 = TagFactory.create("INT1", Integer.class);
	Tag<Integer> INT2 = TagFactory.create("INT2", Integer.class);
	Tag<Integer> INT3 = TagFactory.create("INT3", Integer.class);
	Tag<Integer> INT4 = TagFactory.create("INT4", Integer.class);
	Tag<Integer> INT5 = TagFactory.create("INT5", Integer.class);
	Tag<Integer> INT6 = TagFactory.create("INT6", Integer.class);
	Tag<Integer> INT7 = TagFactory.create("INT7", Integer.class);

	Tag<E> ENUM1 = TagFactory.create("ENUM1", E.class);
	Tag<E> ENUM2 = TagFactory.create("ENUM2", E.class);
	Tag<E> ENUM3 = TagFactory.create("ENUM3", E.class);
	Tag<E> ENUM4 = TagFactory.create("ENUM4", E.class);
	Tag<E> ENUM5 = TagFactory.create("ENUM5", E.class);

	Tag<Double> DOUBLE1 = TagFactory.create("DOUBLE1", Double.class);
	Tag<Double> DOUBLE2 = TagFactory.create("DOUBLE2", Double.class);
	Tag<Double> DOUBLE3 = TagFactory.create("DOUBLE3", Double.class);
	Tag<Double> DOUBLE4 = TagFactory.create("DOUBLE4", Double.class);
	Tag<Double> DOUBLE5 = TagFactory.create("DOUBLE5", Double.class);
	Tag<Double> DOUBLE6 = TagFactory.create("DOUBLE6", Double.class);
	Tag<Double> DOUBLE7 = TagFactory.create("DOUBLE7", Double.class);

	Tag<String> STRING1 = TagFactory.create("STRING1", String.class);
	Tag<String> STRING2 = TagFactory.create("STRING2", String.class);
	Tag<String> STRING3 = TagFactory.create("STRING3", String.class);
	Tag<String> STRING4 = TagFactory.create("STRING4", String.class);
	Tag<String> STRING5 = TagFactory.create("STRING5", String.class);
	Tag<String> STRING6 = TagFactory.create("STRING6", String.class);
	Tag<String> STRING7 = TagFactory.create("STRING7", String.class);

}
