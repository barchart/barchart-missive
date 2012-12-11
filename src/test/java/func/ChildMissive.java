package func;

import com.barchart.missive.core.Tag;
import com.barchart.missive.fast.MissivePool;

public class ChildMissive extends ParentMissive {
	
	public static final MissivePool<ChildMissive> tpool =
			new MissivePool<ChildMissive>(100){};
	
	public static ChildMissive next() {
		return tpool.next();
	}
	
	public ChildMissive() {
		include(new Tag<?>[]{FuncSpec.ChildProp});
	}

}
