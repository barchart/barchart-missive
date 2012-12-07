package func;

public class Test {

	public static void main(final String[] args) {
		
		final long testSize = 100 * 1000 * 1000;
		
		/* Warmup */
//		for(int i = 0; i < testSize; i++) {
//			ParentMissive x = new ParentMissive();
//		}
//		
//		long start = System.currentTimeMillis();
//		for(int i = 0; i < testSize; i++) {
//			ParentMissive x = new ParentMissive();
//		}
//		System.out.println(System.currentTimeMillis() - start);
		
		/* Warmup */
		for(int i = 0; i < testSize; i++) {
			ParentMissive x = ParentMissive.next();
		}
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < testSize; i++) {
			ParentMissive x = ParentMissive.next();
		}
		System.out.println(System.currentTimeMillis() - start);
		
		/* Warmup */
		for(int i = 0; i < testSize; i++) {
			ChildMissive x = ChildMissive.next();
		}
		
		start = System.currentTimeMillis();
		for(int i = 0; i < testSize; i++) {
			ChildMissive x = ChildMissive.next();
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
}
