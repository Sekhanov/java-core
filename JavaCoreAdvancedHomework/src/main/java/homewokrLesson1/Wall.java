package homewokrLesson1;

public class Wall extends Obstacle {

	private int heigth;

	public Wall(String name, int heigth) {
		super(name);
		this.heigth = heigth;
	}

	@Override
	public boolean pass(Person person) {
		return person.getJumpHeight() >= heigth ? true : false;
	}

}
