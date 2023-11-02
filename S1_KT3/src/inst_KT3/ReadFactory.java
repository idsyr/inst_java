package inst_KT3;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class ReadFactory {
	public static <T> void read(HashSet<String> words, String path,
			Filter bad_simbols, Filter bad_words)
	{
		try(FileReader reader = new FileReader(path))
		{
			int c;
			String word = new String();
			while((c=reader.read())!=-1){	
				if((char)c == ' ' || (char)c == '\n' || simbol_target(bad_simbols, (char)c))
				{
					if(word_target(bad_words, word))
						 words.add(word);
					word = "";
					continue;
				}
				word += (char)c;
			} 
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	public static <T> void read(HashMap<String, String> words, String path,
			Filter bad_simbols, Filter bad_words)
	{	
		try(FileReader reader = new FileReader(path))
		{
			int c;
			String word = new String();
			HashMap<Integer, String> first = new HashMap<Integer, String>();
			HashMap<Integer, String> second = new HashMap<Integer, String>();
			Integer i = 0;
			while((c=reader.read())!='~'){
				if((char)c=='\n')
					continue;			
				if((char)c=='.')
				{//System.out.println(word);
					i++;
					first.put(i, word);
					word = "";
					continue;
				}
				word += (char)c;
			}
			Integer k = 0;
			while((c=reader.read())!=-1 && k<=i){
				if((char)c=='\n')
					continue;
				if((char)c=='.')
				{//System.out.println(word);
					k++;
					second.put(k, word);
					word = "";
					continue;
				}
				
				word += (char)c;
			}
			for(Integer a = 0; a<=i; a++)
			{
				//System.out.println(first.get(a) + ' ' + second.get(a));
				words.put(first.get(a), second.get(a));
			}
				
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	private static boolean simbol_target(Filter bad_simbols, char tec)
	{
		if(bad_simbols==null)
			return false;
		for(HashSet<String> i : bad_simbols.i)
		{
			if(i.contains(Character.toString(tec)))
				return true;
		}
		return false;
	}
	private static boolean word_target(Filter bad_words, String tec)
	{
		if(bad_words==null)
			return true;
		for(HashSet<String> i : bad_words.i)
		{
			if(i.contains(tec))
				return false;
		}
		return true;
	}

}

















