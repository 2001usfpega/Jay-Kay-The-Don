package com.revature.constructor;
/*
 * Constructors
 * how we instantiate
 * no return type
 * default constructor is created if none is specified
 */

public class ConstructorDemo {
	public static void main(String[] args) {
		C c = new C();
	}

}

class A{
	A(){
		System.out.println("inside A's constructor");
	}
}
class B extends A{
	/*
	 * B(){}
	 */
	B(){
		System.out.println("inside B's constructor");
	}
}
class C extends B{
	C(){
		System.out.println("inside C's constructor");
	}
}