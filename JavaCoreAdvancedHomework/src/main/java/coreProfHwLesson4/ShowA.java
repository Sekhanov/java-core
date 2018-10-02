package coreProfHwLesson4;

public class ShowA implements Runnable {
	
	private Monitor monitor;

	public ShowA(Monitor monitor) {
		this.monitor = monitor;
		new Thread(this, "ThreadA").start();
	}

	@Override
	public void run() {
		while(true) {
			synchronized(monitor) {
				while(monitor.isBlockA()) {
					try {
						monitor.wait();
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("A");
				monitor.setBlockA(true);
				monitor.setBlockB(false);
				monitor.notifyAll();
			}
		}
		
	}
	
	
}
