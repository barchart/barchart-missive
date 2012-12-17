/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package func;

import com.barchart.missive.core.Tag;
import com.barchart.missive.fast.Missive;
import com.barchart.missive.fast.MissivePool;

public class ParentMissive extends Missive {

	public static final MissivePool<ParentMissive> tpool = 
			new MissivePool<ParentMissive>(100){};
	
	public static ParentMissive next() {
		return tpool.next();
	}
	
	public ParentMissive() {
		super(new Tag<?>[]{FuncSpec.ParentProp});
	}
	
}
