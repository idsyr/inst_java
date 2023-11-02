package inst_KT3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PathManager {
	String file = new String();
	String directory = new String();
	PathManager(String what){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(what + ": input a path: ");
		System.out.print(">>");
		String path_full = new String();
		try {path_full = br.readLine();} catch (IOException ex) 
		{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
		int i = path_full.length()-1;
		for(; i > 0; i--)
		{
			if(	//path_full.charAt(i)=='/' || 
				path_full.charAt(i)==File.separatorChar)
			{
				i++;
				break;
			}	
		}
		
		directory = path_full.substring(0, i);
		file = path_full.substring(i, path_full.length());
	}
}
