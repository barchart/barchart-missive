package func;

import java.util.concurrent.atomic.AtomicInteger;

import com.barchart.missive.Tag;
import com.barchart.missive.fast.Missive;

public class ParentMissive extends Missive {

	private static int POOL_SIZE = 100;
	private static ParentMissive[] pool = new ParentMissive[POOL_SIZE];
	private static AtomicInteger counter = new AtomicInteger(0);
	
	static {
		for(int i = 0; i < POOL_SIZE; i++) {
			pool[i] = new ParentMissive();  // use Flywheight constructor
		}
	}
	
	public static ParentMissive next() {
		if(counter.get() == POOL_SIZE - 1) {
			counter.set(0);
		}
		return pool[counter.getAndIncrement()];
	}
	
	public ParentMissive() {
		super(new Tag<?>[]{FuncSpec.ParentProp});
		//include(new Tag<?>[]{FuncSpec.ParentProp});
	}
	
}
