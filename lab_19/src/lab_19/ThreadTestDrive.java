package lab_19;

class ThreadTestDrive{
	public static void main(String[] args) {
		Runnable theJob = new MyRunnable();
		Thread t = new Thread(theJob);
		t.start();
		System.out.println("return to the main method");
	}
}
class MyRunnable implements Runnable {
	public void run() {
		go();
	}
	public void go() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doMore();
	}
	public void doMore() {
		System.out.println("at the top of the stack");
	}
}