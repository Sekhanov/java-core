package race;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class Race {
	
	@Getter
	private List<Stage> stages;
	
	public Race(Stage...stages) {
		this.stages = Arrays.asList(stages);
	}
	
	
	
}
