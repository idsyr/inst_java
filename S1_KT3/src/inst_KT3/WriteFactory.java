package inst_KT3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class WriteFactory {
	public static <T> void write(HashSet<String> words, String path, Dictionary translate)
	{
		try(FileWriter writer = new FileWriter(path, false))
		{
			for(String word : words)
			{
				if(translate == null)
				{
					if(word.length()<3)
						continue;
					writer.write(word + '.' + '\n');
				}
				else
				{
					String tw = translate_word(translate, word);
					if(tw=="no")
						continue;
					writer.write(String.format("%-20s%-30s\n", word, tw));
				}
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	private static String translate_word(Dictionary translate, String tec)
	{
		String temp = new String();
		for(HashMap<String, String> i : translate.i)
			if(i.containsKey(tec))
				temp+='\t' + i.get(tec);
		return temp;
	}
}
