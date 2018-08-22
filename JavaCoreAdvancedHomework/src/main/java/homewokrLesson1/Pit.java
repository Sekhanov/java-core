package homewokrLesson1;

public class Pit extends Obstacle {

	private int width;

	/**
	 * @param name  название препятствия
	 * @param width ширина ямы
	 */
	public Pit(String name, int width) {
		super(name);
		this.width = width;
	}

	@Override
	public boolean pass(Person person) {
		return person.getJumpWdth() >= width ? true : false;
	}

}
