/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import com.barchart.missive.core.Tag;

public interface FuncSpec {

	Tag<String> ParentProp = Tag.create("ParentProperty", String.class);
	Tag<String> ChildProp = Tag.create("ChildProperty", String.class);

}
