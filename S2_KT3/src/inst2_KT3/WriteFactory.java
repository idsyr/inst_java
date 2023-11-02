package inst2_KT3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class WriteFactory {
	public static <T> String write(HashSet<String> words, String path, Dictionary translate){
		String status = "+";
		try(FileWriter writer = new FileWriter(path, true)){
			for(String word : words){
				if(translate == null){
					if(word.length()<1)
						continue;
					writer.write(word + 
							'.' + '\n'
							//'\n'
							);
				}
				else{
					String tw = translate_word(translate, word);
					if(tw=="no")
						continue;
					writer.write(String.format("%-20s%-30s\n", word, tw));
				}
			}
		}
		catch(IOException ex) {System.out.println(ex.getMessage()); status = ex.getMessage();}
		return status;
	}
	public static String translate_word(Dictionary translate, String tec)
	{
		//tec +='.';
		String temp = new String();
		for(HashMap<String, String> i : translate.i)
			if(i.containsKey(tec))
				temp+=i.get(tec);
		return temp;
	}
}
