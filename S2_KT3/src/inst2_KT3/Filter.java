package inst2_KT3;

import java.util.ArrayList;
import java.util.HashSet;

public class Filter {
	ArrayList<HashSet<String>> i = new ArrayList<HashSet<String>>();
	String status = "";
	public Filter(ArrayList<String> DictionaryList){
		for(String d : DictionaryList)
		{
			HashSet<String> temp = new HashSet<String>();
			status += ReadFactory.read(temp, d, null, null);
			i.add(temp);
		}
	}
}
