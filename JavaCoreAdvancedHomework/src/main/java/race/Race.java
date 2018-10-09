package race;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import lombok.Getter;

public class Race {
	
	@Getter
	CountDownLatch startRaceCountDownLatch;	
	@Getter
	CountDownLatch finishRaceCountDownLatch;	
	@Getter
	private List<Stage> stages;
	
	public Race(int racers, Stage...stages) {
		this.stages = Arrays.asList(stages);
		this.finishRaceCountDownLatch = new CountDownLatch(racers);
		this.startRaceCountDownLatch = new CountDownLatch(racers);
	}
	
	
	
}
