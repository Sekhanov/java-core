package race;

import java.util.concurrent.CountDownLatch;

import lombok.Getter;

public class Car implements Runnable {

	
	private volatile static int CAR_COUNTER;	
	private volatile static boolean IS_WINNER;
	@Getter
	private int speed;
	@Getter
	private String name;
	private Race race;
	
	static {
		CAR_COUNTER = 0;
		IS_WINNER = false;
	}
	
	public Car(Race race, int speed) {
		this.race = race;
		this.name = "car " + CAR_COUNTER;
		this.speed = speed;
		CAR_COUNTER++;
	}



	@Override
	public void run() {
		System.out.println("Участник " + name + " готовится");
		try {
			Thread.sleep(300 + (int) (Math.random() * 1000));
			System.out.println("Участник " + name + " готов");
			race.getStartRaceCountDownLatch().countDown();
			race.getStartRaceCountDownLatch().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Stage stage : race.getStages()) {
			stage.go(this);
		}
		if(!IS_WINNER) {
			System.out.println(name + " win!");
			IS_WINNER = true;
		}
		race.getFinishRaceCountDownLatch().countDown();
	}

}