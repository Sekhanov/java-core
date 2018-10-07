package race;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{

	private Semaphore tonnelSemaphore;
	
	public Tunnel(int lenght, int trafficCapacity) {
		this.lenght = lenght;
		this.tonnelSemaphore = new Semaphore(trafficCapacity);
		this.descripiton = "тоннель" + lenght + "метров";
	}
	
	@Override
	public void go(Car car) {
		try {
			System.out.println(car.getName() + " готовится к этапу: " + descripiton);
			tonnelSemaphore.acquire();
			System.out.println(car.getName() + " начал прохождение этапа " + descripiton);
			Thread.sleep((int) (lenght / car.getSpeed() * 1000));
			tonnelSemaphore.release();
			System.out.println(car.getName() + " закончил прохождение этапа " + descripiton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
