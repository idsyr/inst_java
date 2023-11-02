package inst_KT3;

public class TestPathManager {
	TestPathManager()
	{
		PathManager test = new PathManager("input a path for test, if he is valid you can use this program on your system");
		System.out.println("directory is: "+ test.directory + "\n" + "filename is: " + test.file);
	}
}
