package lab_12;

public class TestExceptions {
	public static void main(String[] args)
	{
		String test = "no";
		try {
			System.out.println("begin of try");
			doRisky(test);
			System.out.println("end of try");
		}
		catch(ScaryException se) {System.out.println("catch scary exception");}
		finally {System.out.println("finally");}
		System.out.println("end of main");
	}
	static void doRisky(String test) throws ScaryException
	{
		System.out.println("begin of risky method");
		if("yes".equals(test)) {throw new ScaryException("from doRisky");}
		System.out.println("end of risky method");
		return;
	}
	
}
class ScaryException extends Exception{
	ScaryException(String msg)
	{
		System.out.println("scary  exception " + msg);
	}
}
