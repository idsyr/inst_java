/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.inst_kt3;


/**
 *
 * @author ids
 */
public class Inst_KT3 {
}

class StaticSuper{
	static{
		System.out.println("Родительский статический блок");
	}
	StaticSuper(){
		System.out.println("Родительский конструктор");
	}
}

class StaticTests extends StaticSuper{
	static int rand;
	static{
		rand = (int)(Math.random()*6);
	}
	StaticTests(){
		System.out.println("КОнструктор");
	}
	public static void main(String[] args){
		System.out.println("Внутри main");
		StaticTests st = new StaticTests();
	}
}