package main;

import homewokrLesson1.Cource;
import homewokrLesson1.Obstacle;
import homewokrLesson1.Person;
import homewokrLesson1.Person.Gender;
import homewokrLesson1.Pit;
import homewokrLesson1.Team;
import homewokrLesson1.Wall;

public class MainClass {

	public static void main(String[] args) {
		doHomeworkLesson1();
	}

	private static void doHomeworkLesson1() {
		Obstacle[] obstacles = {new Wall("Everest", 140), new Pit("Grand canyon", 200)};
		Cource cource = new Cource(obstacles, "Armageddon");
		Person[] members1 = { new Person("Ivan Petrov", 160, 190, Gender.MALE), new Person("Dmitriy Komov", 150, 201, Gender.MALE),
				new Person("Mariya Kramina", 141, 220, Gender.FAMALE), new Person("Evgenity Vanin", 150, 215, Gender.MALE)};
		Person[] members2 = {new Person("Vasiliy Dudkin", 120, 170, Gender.MALE), new Person("Kristina Vyugina", 150, 210, Gender.FAMALE),
				new Person("Anna Sanina", 130, 220, Gender.FAMALE), new Person("Aleksey Krugov", 160, 230, Gender.FAMALE)};
		Team team = new Team("BestTeam", members1);
		Team team2 = new Team("Arrow", members2);
		team.passCource(cource);
		team2.passCource(cource);
		team.showResults();
		team2.showResults();
	}

}
