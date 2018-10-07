package race;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import lombok.Getter;

public class Car implements Runnable {

	
	private static int CAR_COUNTER;	
	private static boolean isWinner;
	@Getter
	private int speed;
	@Getter
	private String name;
	private Race race;
	private CountDownLatch startRaceCountDownLatch;
	private CountDownLatch finishRaceCountDownLatch;

	
	static {
		CAR_COUNTER = 0;
		isWinner = false;
	}
	
	public Car(Race race, int speed, CountDownLatch startRaceCountDownLatch, CountDownLatch finishRaceCountDownLatch) {
		this.race = race;
		this.name = "car " + CAR_COUNTER;
		this.speed = speed;
		this.finishRaceCountDownLatch = finishRaceCountDownLatch;
		this.startRaceCountDownLatch = startRaceCountDownLatch;
		CAR_COUNTER++;
	}



	@Override
	public void run() {
		System.out.println("Участник " + name + " готовится");
		try {
			Thread.sleep(300 + (int) (Math.random() * 1000));
			System.out.println("Участник " + name + " готов");
			startRaceCountDownLatch.countDown();
			startRaceCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Stage stage : race.getStages()) {
			stage.go(this);
		}
		if(!isWinner) {
			System.out.println(name + " win!");
			isWinner = true;
		}
		finishRaceCountDownLatch.countDown();
	}

}