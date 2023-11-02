/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inst_11;
import java.util.Random;

/**
 *
 * @author ids
 */
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
		System.out.println("Статический блок "+rand);
	}
	StaticTests(){
		System.out.println("КОнструктор");
	}
	public static void main(String[] args){
		System.out.println("Внутри main");
		StaticTests st = new StaticTests();
	}
}
