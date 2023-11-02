package lab_22;
import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
	public String sayHello() {
		return "server say: hello";
	}
	public MyRemoteImpl() throws RemoteException{}
	public static void main(String[] args) {
		try {
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("Hello", service);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
