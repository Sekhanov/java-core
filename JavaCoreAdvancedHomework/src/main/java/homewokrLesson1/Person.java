package homewokrLesson1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Getter
	private String name;

	/**
	 * Высота прыжка в см
	 */
	@Getter
	private int jumpHeight;

	/**
	 * Длина прыжка в см
	 */
	@Getter
	private int jumpWdth;

	@Getter
	private Gender gender;

	public enum Gender {
		MALE, FAMALE
	}
}
