package homewokrLesson1;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Obstacle {

	@Getter
	@NonNull
	protected String name;

	/**
	 * 
	 * @param person участник, проходящий препятствие
	 * @return true если прошел
	 */
	public abstract boolean pass(Person person);

}
