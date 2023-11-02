/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.inst_8;

/**
 *
 * @author ids
 */
public class Inst_8 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
		Of76.run();
    }
}

interface Nose
	{public int iMethod();}

abstract class Picasso implements Nose
	{public int iMethod() {return 7;}}

class Clowns extends Picasso {}

class Acts extends Picasso
	{public int iMethod() {return 5;}}

class Of76 extends Clowns
{
    public static void run() {
        Nose[] i = new Nose[3];
        i[0] = new Acts();
        i[1] = new Clowns();
        i[2] = new Of76();
        for (int x = 0; x < 3; x++) {
            System.out.println(i[x].iMethod() + " " + i[x].getClass());
        }
    }
}


