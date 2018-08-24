package homewokrLesson1;

import homewokrLesson1.Person.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Team {

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Person[] members;
	private Person[] passedMemgers;
	private boolean isPass;

	public Team(String name, Person[] members) {
		this.name = name;
		this.members = members;
		passedMemgers = new Person[members.length];
		for (int i = 0; i < passedMemgers.length; i++) {
			passedMemgers[i] = new Person();
		}
	}

	/**
	 * 
	 * @param cource полоса препятствий для прохождения
	 * @return большая часть команды проходит полосу препятствий - true
	 */
	public boolean passCource(Cource cource) {
		int counter = 0;
		Obstacle[] obstacles = cource.getObstacles();
		int passedObstacles = 0;
		System.out.println("Команда " + name + " начала прохождение полосы препятствий " + cource.getName());
		for (int i = 0; i < members.length; i++) {
			for (int j = 0; j < obstacles.length; j++) {
				if (obstacles[j].pass(members[i])) {
					String gender = members[i].getGender() == Gender.MALE ? " преодолел " : " преодолела ";
					System.out.println(members[i].getName() + gender + obstacles[j].getName());
					passedObstacles++;
					if (passedObstacles == obstacles.length) {

						passedMemgers[counter] = members[i];
						counter++;
					}
				} else {
					String gender = members[i].getGender() == Gender.MALE ? "смог" : "смогла";
					System.out
							.println(members[i].getName() + " не " + gender + " преодолеть " + obstacles[j].getName());
					break;

				}
			}
			passedObstacles = 0;
		}
		System.out.println("Команда " + name + " закончила прохождение полосы препятствий " + cource.getName());
		System.out.println("-----------------------------------------------------------");
		isPass = counter > (members.length / 2) ? true : false;
		return isPass;
	}

	public void showResults() {
		if (isPass) {
			System.out.println("Команде " + name + " удалость пройти полосу препятствий! Наши победители: ");
			for (int i = 0; i < passedMemgers.length; i++) {
				if (passedMemgers[i].getName() != null) {
					System.out.println(passedMemgers[i].getName());
				}
			}
		} else {
			System.out.println(
					"Команде " + name + " не удалось выигать. Полосу препятствий прошли лишь следующие участники:");
			for (int i = 0; i < passedMemgers.length; i++) {
				if (passedMemgers[i].getName() != null) {
					System.out.println(passedMemgers[i].getName());
				}
			}
		}
	}

}
