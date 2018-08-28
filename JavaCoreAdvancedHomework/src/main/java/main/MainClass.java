package main;

import java.util.Scanner;

import homewokrLesson1.Cource;
import homewokrLesson1.Obstacle;
import homewokrLesson1.Person;
import homewokrLesson1.Person.Gender;
import homeworkLesson2.MyArray;
import homeworkLesson2.MyArrayDataException;
import homeworkLesson2.MyArraySizeException;
import homewokrLesson1.Pit;
import homewokrLesson1.Team;
import homewokrLesson1.Wall;

public class MainClass {

	public static void main(String[] args) {
//		doHomeworkLesson1();
		doHomeworkLesson2();

	}

	private static void doHomeworkLesson1() {
		Obstacle[] obstacles = { new Wall("Everest", 140), new Pit("Grand canyon", 200) };
		Cource cource = new Cource(obstacles, "Armageddon");
		Person[] members1 = { new Person("Ivan Petrov", 160, 190, Gender.MALE),
				new Person("Dmitriy Komov", 150, 201, Gender.MALE),
				new Person("Mariya Kramina", 141, 220, Gender.FAMALE),
				new Person("Evgenity Vanin", 150, 215, Gender.MALE) };
		Person[] members2 = { new Person("Vasiliy Dudkin", 120, 170, Gender.MALE),
				new Person("Kristina Vyugina", 150, 210, Gender.FAMALE),
				new Person("Anna Sanina", 130, 220, Gender.FAMALE),
				new Person("Aleksey Krugov", 160, 230, Gender.FAMALE) };
		Team team = new Team("BestTeam", members1);
		Team team2 = new Team("Arrow", members2);
		team.passCource(cource);
		team2.passCource(cource);
		team.showResults();
		team2.showResults();
	}

	private static void doHomeworkLesson2() {
		int result = 0;
		MyArray myArray = new MyArray();
		String[][] stringArr;
		int arraySizeDemension1;
		int arraySizeDemension2;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите размер первого измерения массива:");
		arraySizeDemension1 = scanner.nextInt();
		System.out.println("Введите размер второго измерения массива:");
		arraySizeDemension2 = scanner.nextInt();
		stringArr = new String[arraySizeDemension1][arraySizeDemension2];

		for (int i = 0; i < stringArr.length; i++) {
			for (int j = 0; j < stringArr.length; j++) {
				//TODO ЗДЕСЬ ЗАКОНЧИЛ
				System.out.printf("Введите число для ячейки [%d][%d]", i, j);
				System.out.println();
				stringArr[i][j] = scanner.next();
			}
		}

		while (result == 0) {
			try {
				result = myArray.processArray(stringArr);
			} catch (MyArraySizeException e) {
				e.printStackTrace();
				result = -1;				
			} catch (MyArrayDataException e) {				
				System.out.println(e.getMessage());
				System.out.println("Введите в эту ячейку число от 0 до 9");				
				stringArr[e.getFirsDemensionArrayCell()][e.getSecondDemendionArrayCell()] = scanner.next();
			}
		}
		scanner.close();

		System.out.println("Сумма всех элементов масиива составляет: " + result);
	}

}
