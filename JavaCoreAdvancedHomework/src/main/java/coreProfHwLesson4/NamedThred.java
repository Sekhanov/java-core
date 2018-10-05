package coreProfHwLesson4;

public class NamedThred extends Thread {

	private Controller controller;
	private int count;

	public NamedThred(String name, int count, Controller controller) {
		this.controller = controller;
		this.count = count;
		setName(name);

	}

	@Override
	public void run() {
		synchronized (controller) {
			for (int i = 0; i < count; i++) {
				try {
					while (!controller.getCurrent().equals(getName())) {
						controller.wait();
					}
					System.out.print(getName());
					Thread.sleep(100);
					controller.move();
					controller.notifyAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
