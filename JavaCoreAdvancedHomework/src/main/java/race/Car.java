package race;

import lombok.Getter;

public class Car implements Runnable {

	private volatile static int CAR_COUNTER;	
	@Getter
	private int speed;
	@Getter
	private String name;
	private Race race;
	
	static {
		CAR_COUNTER = 0;
	}
	
	public Car(Race race, int speed) {
		this.race = race;
		this.name = "car " +  ++CAR_COUNTER;
		this.speed = speed;
	}




	@Override
	public void run() {		
		race.prepareRace(name);
		race.getStages().forEach(stage -> stage.go(this));
		race.finishRace(name);	
	}

}