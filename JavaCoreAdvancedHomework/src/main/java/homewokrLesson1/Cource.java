package homewokrLesson1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Cource {

	@Getter
	private Obstacle[] obstacles;
	@Getter
	private String name;
}
