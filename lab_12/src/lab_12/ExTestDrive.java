package lab_12;

class MyEx extends Exception {
	MyEx()
	{
		System.out.print("r");
	}
}
public class ExTestDrive{
	public static void main(String[] args) throws MyEx
	{
		System.out.print("t");
		String test = "no";
		try {doRisky(test);} catch (MyEx e) {System.out.print("o");}
		doRisky(test);
		System.out.print("w");
		System.out.println("s");

	}
	static void doRisky(String t) throws MyEx
	{
		System.out.print("h");
		if("yes".equals(t)) throw new MyEx();
		System.out.print("a");
	}
}
