package func;

import com.barchart.missive.Tag;
import com.barchart.missive.fast.ObjectPool;

public class ChildMissive extends ParentMissive {
	
	public static final ObjectPool<ChildMissive> tpool =
			new ObjectPool<ChildMissive>(10000){};
	
	public static ChildMissive next() {
		return tpool.next();
	}
	
	public ChildMissive() {
		include(new Tag<?>[]{FuncSpec.ChildProp});
	}

}
