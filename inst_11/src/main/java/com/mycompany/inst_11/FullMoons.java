/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inst_11;
import java.util.*;
import java.io.*;

import static java.lang.System.out;
/**
 *
 * @author ids
 */
public class FullMoons {
	static int DAY_IM = 1000*60*60*24;
	public static void run(){
		Calendar c = Calendar.getInstance();
		c.set(2004, 0, 7, 15, 40);
		long day1 = c.getTimeInMillis();
		c.setTimeInMillis(day1);
		for(int i = 0; i<100; i++){
			day1 += (DAY_IM*29.52);
			c.setTimeInMillis(day1);
			out.println(String.format("Полноуние было в %tc", c));
		}
	}
}
