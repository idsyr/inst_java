/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inst_7;

/**
 *
 * @author ids
 */
class Monster{
	boolean frighten(int x) {
		System.out.println("grrrrr");
		return false;
	}
}

class Vampire extends Monster{
	boolean frighten(int x){
		System.out.println("bite?");
		return false;
	}
}

class Dragon extends Monster{
	boolean frighten(int degree){
		System.out.println("fiery breath");
		return true;
	}
}

public class MonsterTestDrive {
	public static void run() {
		System.out.println("");
		System.out.println("MonsterTestDrive:");
		Monster [] ma = new Monster[3];
		ma[0] =  new Vampire();
		ma[1] =  new Dragon();
		ma[2] = new Monster();
		for(int x = 0; x < 3; x++) {
			ma[x].frighten(x);
		}
	}
}
