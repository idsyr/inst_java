package lab_19;
import java.io.*;
import java.net.*;

public class DailyAdviceServer {
	String[] adviceList = {"Eat smaller portions", "Buy skinny jeans. No, they don't make you fuller", 
			"Two words: not good", "Be honest at least today. Tell your boss everything you *really* think about him", "Maybe you should pick up a different hairstyle"};
	public void go() {
		try {
			ServerSocket serverSock = new ServerSocket(4242);
			while(true) {
				Socket sock = serverSock.accept();
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				System.out.println(advice);
			}
		} catch(IOException ex) {ex.printStackTrace();}
	}
	private String getAdvice() {
		int random = (int) (Math.random()*adviceList.length);
		return adviceList[random];
	}
	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}
}
