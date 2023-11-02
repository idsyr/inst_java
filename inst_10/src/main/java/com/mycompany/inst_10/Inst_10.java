/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.inst_10;
import java.util.Random;

/**
 *
 * @author ids
 */
public class Inst_10 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
class Computer{
	CPU cpu;
	RAM ram;
	MotherBoard motherBoard;
	PowerSupply powerSupply;
	
	public PC_element[] con_devices;
	
	public Computer(CPU_arch arch, int RAM_size, MB_format MBF){
		if(arch == CPU_arch.MIPS)
			this.cpu = new CPU_MIPS();
		else
			this.cpu = new CPU_X86_64();
		this.motherBoard = new MotherBoard(MBF);
		this.ram = new RAM(RAM_size);
		this.powerSupply = new PowerSupply();
		con_devices = new PC_element[motherBoard.conNumber]; 
	};
	
	public String connect(PC_element device){
		Random conFail = new Random();
		if(conFail.nextInt(100) == 99)
			return "RandomError";
		if(!motherBoard.has(device.con))
			return "doesntHaveConnector";
		motherBoard.use(device.con);
		for(int i = 0; i<con_devices.length; i++)
		{
			if(con_devices[i] == null)
				{
					con_devices[i] = device;
					break;
				}
		}
		return "Success";
	};
	
}
class PC_element{
	public Connector con;
}

class CPU extends PC_element{
	Cache l3;
	
	enum CacheType{
			L1,
			L2,
			L3,
			L4
		}
	class Cache{
		
		CacheType cacheType;
		int capacity;
		Cache(int capacity, CacheType type)
		{
			this.capacity = capacity;
			cacheType = type;
		}
	}
	class Core{
		Cache l1;
		Cache l2;
		Core(){
			l1 = new Cache(1024, CacheType.L1);
			l2 = new Cache(1024, CacheType.L2);
		};
	}

	
}
class RAM extends PC_element{
	int capacity;
	public RAM(int size)
	{
		this.capacity = size;
	}
}
class MotherBoard extends PC_element{
	public Connector[] connectors;
	int conNumber;
	MotherBoard(MB_format MBF){
		if(MBF == MB_format.ATX)
		{
			
		}
		if(MBF == MB_format.miniATX)
		{
		
		}
	};
	public boolean has(Connector con){return true;};
	public void use(Connector con){};
}
class PowerSupply extends PC_element{}

class GPU extends PC_element{
	void drawSomething(){};
}
class Monitor extends PC_element{
	void showSomethidg(){};
}
class Keyboard extends PC_element{
	int key;
	int getKey(){return key;};
}
class Mouse extends PC_element{
	Position pos;
	Position getPos(){return pos;};
}

class CPU_MIPS extends CPU{}
class CPU_X86_64 extends CPU{}

enum CPU_arch{
	MIPS,
	X86_64
}

enum MB_format{
	ATX,
	miniATX
}

enum Connector{
	USB,
	LGA775,
	PGA116,
	PS_2
}

enum ConnectErrorType{
	
}

class Position{
	int x;
	int y;
}