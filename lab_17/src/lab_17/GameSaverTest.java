package lab_17;
import java.io.*;
public class GameSaverTest {
	public static void main(String[] args) {
		GameCharacter one = new GameCharacter(50, "elf", new String[] {"bow", "sword", "CNUCKLES"});
		GameCharacter two = new GameCharacter(200, "troll", new String[] {"hands", "big axe"});
		GameCharacter three = new GameCharacter(120, "wizard", new String[] {"spells", "invisibility"});
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			os.writeObject(one); os.writeObject(two); os.writeObject(three); os.close();
		} catch(IOException ex) {ex.printStackTrace();}
		one = null; two = null; three = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
			GameCharacter oneRestore = (GameCharacter) is.readObject();
			GameCharacter twoRestore = (GameCharacter) is.readObject();
			GameCharacter threeRestore = (GameCharacter) is.readObject();
			
			System.out.println(oneRestore.getType());
			System.out.println(twoRestore.getType());
			System.out.println(threeRestore.getType());
		} catch(Exception ex) {ex.printStackTrace();}
	}
}