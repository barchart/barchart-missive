package func;

public class TestCast {

	public static void main(final String[] args) {
		
		ParentMissive p = ParentMissive.next();
		
		visit(p.castAsSubclass(ChildMissive.next()));
		
		final long testSize = 10 * 1000 * 1000;
		final int numTests = 5;
		
		for(int j = 0; j < numTests; j++) {
		
			for(int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(new ChildMissive()));
			}
			
			long start = System.currentTimeMillis();
			for(int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(new ChildMissive()));
			}
			System.out.println(System.currentTimeMillis() - start);
			
			for(int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(ChildMissive.next()));
			}
			
			start = System.currentTimeMillis();
			for(int i = 0; i < testSize; i++) {
				visit(p.castAsSubclass(ChildMissive.next()));
			}
			System.out.println(System.currentTimeMillis() - start);
			
		}
	}

	public static void visit(final ParentMissive m) {
		System.out.println("Parent Visit");
	}
	
	public static void visit(final ChildMissive m) {
		//System.out.println("Child Visit");
	}
	
}
