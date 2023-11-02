/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inst_7;

/**
 *
 * @author ids
 */
class Boat{
	private int length;
	
	public void setLength(int len){
		length = len;
	}
	
	public int getLength(){
		return length;
	}
	
	public void move(){
		System.out.print("drift ");
	}
}

class Rowboat extends Boat{
	public void rowTheBoat(){
		System.out.print("Come on, natasha ");
	}
}

class Sailboat extends Boat{
	public void move(){
		System.out.print("Set sail ");
	}
}

public class TestBoats{
	public static void run(){
		System.out.println("");
		System.out.println("TestBoats:");
		Boat b1 = new Boat();
		Sailboat b2 = new Sailboat();
		Rowboat b3 = new Rowboat();
		b2.setLength(32);
		
		b1.move();
		b3.move();
		b2.move();
	}
}


