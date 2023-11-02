package inst_KT3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
///home/ids/projects/test.txt

public class MyProgram {
	ArrayList<String> piecesDictionary = new ArrayList<String>();
	ArrayList<String> bad_simbols = new ArrayList<String>();
	ArrayList<String> bad_words = new ArrayList<String>();
	
	boolean stdDictOn = true;
	boolean stdBSOn = true;
	boolean stdBWOn = true;
	
	public static void main(String args[])
	{
		MyProgram mp = new MyProgram();
		TestPathManager test = new TestPathManager();
		mp.menu();
	}
	void menu()
	{
		System.out.println("Choose operating mode:");
		System.out.println("1 - find unique and translate");
		System.out.println("2 - configure filter");
		System.out.println("3 - configure dictionary");
		System.out.println("4 - exit");
		System.out.print(">>");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String move = new String();
		try {move = br.readLine();} catch (IOException ex) 
		{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
		switch(move){
		case("1"):
			normal();
			break;
		case("2"):
			configure_filter();
			break;
		case("3"):
			configure_dictionary();
			break;
		case("4"):
			return;
		default:
			System.out.println("undefind command");
			return;
		}
	}
	void normal()
	{
		if(stdDictOn)
			piecesDictionary.add("english.dictionary");
		if(stdBSOn)
			bad_simbols.add("bs.filter");
		if(stdBWOn)
			bad_words.add("bw.filter");
		
		Dictionary ed = new Dictionary(piecesDictionary);
		Filter bs = new Filter(bad_simbols);
		Filter bw = new Filter(bad_words);
		
		PathManager PM = new PathManager("file for find unique words and translate");
		
		HashSet<String> words = new HashSet<>();
		ReadFactory.read(words, PM.directory + PM.file, bs, bw);
		WriteFactory.write(words, PM.directory + "unique of " + PM.file, null);
		words = null;
		
		HashSet<String> words2 = new HashSet<>();
		ReadFactory.read(words2, PM.directory + "unique of " + PM.file, bs, bw);
		WriteFactory.write(words2, PM.directory + "translate of unique of " + PM.file, ed);
		words2 = null;
		
		System.out.println("Check your folder, now you have unique words and translate " + PM.file);
		System.out.println("");
		menu();
	}
	void configure_dictionary()
	{
		System.out.println("Choose configure dictionary move:");
		System.out.println("1 - disable standard eng dictionary");
		System.out.println("2 - add new dictionary for local sesion");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String move = new String();
		try {move = br.readLine();} catch (IOException ex)
		{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
		switch(move){
		case("1"):
			stdDictOn = false; 
			break;
		case("2"):
			System.out.println("input a path to your dictionary:");
			String path = new String();
			try {path = br.readLine();} catch (IOException ex)
			{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
			piecesDictionary.add(path);
			break;
		default:
			System.out.println("undefind command");
			return;
		}
		menu();
	}
	void configure_filter()
	{
		System.out.println("Choose configure filter move:");
		System.out.println("1 - disable standard bad_words filter");
		System.out.println("2 - disable standard bad_simbol filter");
		System.out.println("3 - add new bad_simbols filter for local session");
		System.out.println("4 - add new bad_words filter for local session");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String move = new String();
		try {move = br.readLine();} catch (IOException ex)
		{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
		switch(move){
		case("1"):
			stdBWOn = false; 
			break;
		case("2"):
			stdBSOn = false;
		case("3"):
			System.out.println("input a path to your dictionary:");
			String path = new String();
			try {path = br.readLine();} catch (IOException ex)
			{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
			bad_simbols.add(path);
			break;
		case("4"):
			System.out.println("input a path to your dictionary:");
			String path1 = new String();
			try {path1 = br.readLine();} catch (IOException ex)
			{System.out.println(ex.getMessage()); ex.printStackTrace(); System.exit(-1);}
			bad_words.add(path1);
			break;
		default:
			System.out.println("undefind command");
			return;
		}
		menu();
	}
}
















