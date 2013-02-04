/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import com.barchart.missive.core.Tag;
import com.barchart.missive.fast.FastMissive;
import com.barchart.missive.fast.FastMissivePool;

public class ParentMissive extends FastMissive {

	public static final FastMissivePool<ParentMissive> tpool = 
			new FastMissivePool<ParentMissive>(100){};
	
	public static ParentMissive next() {
		return tpool.next();
	}
	
	public ParentMissive() {
		super(new Tag<?>[]{FuncSpec.ParentProp});
	}
	
}
