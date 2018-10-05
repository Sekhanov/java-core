package coreProfHwLesson4;

public class ShowB implements Runnable{

	private Monitor monitor;

	public ShowB(Monitor monitor) {
		this.monitor = monitor;
		new Thread(this, "ThreadB").start();
	}

	@Override
	public void run() {
		while(true) {
			synchronized(monitor) {
				while(monitor.isBlockB()) {
					try {
						monitor.wait();
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("B");
				monitor.setBlockB(true);
				monitor.setBlockC(false);
				monitor.notifyAll();
			}
		}

		
	}
	
	
	
	
}
