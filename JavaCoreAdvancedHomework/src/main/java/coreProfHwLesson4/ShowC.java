package coreProfHwLesson4;

public class ShowC implements Runnable {

	private Monitor monitor;
	
	public ShowC(Monitor monitor) {
		this.monitor = monitor;
		new Thread(this, "ThreadC").start();
	}

	@Override
	public void run() {
		while(true) {
			synchronized(monitor) {
				while(monitor.isBlockC()) {
					try {
						monitor.wait();
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("C");
				monitor.setBlockC(true);
				monitor.setBlockA(false);
				monitor.notifyAll();
			}
		}
		
	}

}
