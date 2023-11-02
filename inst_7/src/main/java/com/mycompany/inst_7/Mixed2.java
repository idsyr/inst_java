/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inst_7;

/**
 *
 * @author ids
 */
class A{
	int ivar = 7;
	void m1(){
		System.out.print("A's m1, ");
	}
	
	void m2(){
		System.out.print("A's m2, ");
	}
	
	void m3(){
		System.out.print("A's m3, ");
	}
}

class B extends A{
	void m1(){
		System.out.print("B's m1, ");
	}
}

class C extends B{
	void m3(){
		System.out.print("C's m3, " + (ivar + 6));
	}
}

public class Mixed2{
	public static void run(){
		A a = new A();
		B b = new B();
		C c = new C();
		A a2 = new C();
		
		System.out.println("Mixed2:");
		System.out.println("__________________case 1____________________");
		System.out.println("result:");
		b.m1();
		c.m2();
		a.m3();
		
		System.out.println("");
		System.out.println("__________________case 2____________________");
		System.out.println("result:");
		c.m1();
		c.m2();
		c.m3();
		
		System.out.println("");
		System.out.println("__________________case 3____________________");
		System.out.println("result:");
		a.m1();
		b.m2();
		c.m3();
		
		System.out.println("");
		System.out.println("__________________case 4____________________");
		System.out.println("result:");
		a2.m1();
		a2.m2();
		a2.m3();
		
		System.out.println("");
	}
}



