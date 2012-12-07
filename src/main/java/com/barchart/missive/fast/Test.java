package com.barchart.missive.fast;

public class Test {
	
	public static class Parent {
		
		public static String next() {
			return pProp;
		}
		
		public static String pProp = "ParentProperty";
	}
	
	public static class Child extends Parent {
		
		public static String next() {
			return cProp;
		}
		
		public static String cProp = "ChildProperty";
	}
	
	public static void main(final String[] args) {
		
		Parent p = new Parent();
		
		Child c = new Child();
		
		System.out.println(Parent.next());
		System.out.println(Child.next());
		
	}

}
