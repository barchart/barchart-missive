/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import com.barchart.missive.core.Tag;
import com.barchart.missive.fast.FastMissivePool;

public class ChildMissive extends ParentMissive {
	
	public static final FastMissivePool<ChildMissive> tpool =
			new FastMissivePool<ChildMissive>(100){};
	
	public static ChildMissive next() {
		return tpool.next();
	}
	
	public ChildMissive() {
		include(new Tag<?>[]{FuncSpec.ChildProp});
	}

}
