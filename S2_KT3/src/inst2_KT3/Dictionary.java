package inst2_KT3;

import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
	ArrayList<HashMap<String, String>> i = new ArrayList<HashMap<String, String>>();
	String status = "";
	public Dictionary(ArrayList<String> DictionaryList){
		for(String d : DictionaryList)
		{
			HashMap<String, String> temp = new HashMap<String, String>();
			status += ReadFactory.read(temp, d, null, null);
			i.add(temp);
		}
	}
}
