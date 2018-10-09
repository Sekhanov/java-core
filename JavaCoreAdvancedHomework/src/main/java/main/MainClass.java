package main;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import coreProfHwLesson1.Apple;
import coreProfHwLesson1.Box;
import coreProfHwLesson1.GenericsTest;
import coreProfHwLesson1.Orange;
import coreProfHwLesson4.Controller;
import coreProfHwLesson4.Monitor;
import coreProfHwLesson4.ShowA;
import coreProfHwLesson4.ShowB;
import coreProfHwLesson4.ShowC;
import homewokrLesson1.Cource;
import homewokrLesson1.Obstacle;
import homewokrLesson1.Person;
import homewokrLesson1.Person.Gender;
import homewokrLesson1.Pit;
import homewokrLesson1.Team;
import homewokrLesson1.Wall;
import homeworkLesson2.MyArray;
import homeworkLesson2.MyArrayDataException;
import homeworkLesson2.MyArraySizeException;
import homeworkLesson3.ArrayParser;
import homeworkLesson3.PhoneDirectory;
import homeworkLesson4.ChatWindow;
import homeworkLesson5.ConcurrencyTest;
import race.Car;
import race.Race;
import race.Road;
import race.Tunnel;

public class MainClass {

	public static void main(String[] args) {
//		doHomeworkLesson1();
//		doHomeworkLesson2();
//		doHomeworkLesson3();
//		doHomeworkLesson4();
//		doHomeworkLesson5();
		
//		doHomeworkCoreProfLesson1();
//		doHomeworkCoreProfLesson4();
//		doFixHomeworkCoreProfLesson4();
		race();
		
	}
	
	
	
	
	private static void race() {
		final int CARS_COUNT = 10;		
		Race race = new Race(new Road(60), new Tunnel(80, CARS_COUNT / 2), new Road(40));
		for (int i = 0; i < CARS_COUNT; i++) {			
			race.addCar(new Car(race, 20 + (int) (Math.random() * 20)));
		}
		race.startRace();
	}




	private static void doFixHomeworkCoreProfLesson4() {
		Controller controller = new Controller(20, "a", "b", "c");
		controller.start();
	}




	private static void doHomeworkCoreProfLesson4() {
		Monitor monitor = new Monitor();
		new ShowA(monitor);
		new ShowB(monitor);
		new ShowC(monitor);
		
	}




	private static void doHomeworkLesson5() {
		int arrSise = 10000000;
		ConcurrencyTest concurrencyTest = new ConcurrencyTest();
		
		float[] arr1 = new float[arrSise];
		concurrencyTest.fillFloatArr(arr1);		
		long oneThreadProcessTime = concurrencyTest.processTime(concurrencyTest::oneThreadProcessArray, arr1);
		System.out.println("one thead time: " + oneThreadProcessTime + "ms");
		System.out.println(arr1[9999999]);
		
		float[] arr2 = new float[arrSise];
		concurrencyTest.fillFloatArr(arr2);		
		long twoThreadProcessTime = concurrencyTest.processTime(concurrencyTest::twoThreadProcessArray, arr2);
		System.out.println("two thead time: " + twoThreadProcessTime + "ms");
		System.out.println(arr1[9999999]);
	}

	
	private static void doHomeworkLesson4() {
		new ChatWindow();
	}
	

	private static void doHomeworkLesson3() {
		String[] strings = new String[20];
		strings[0] = "apple";
		strings[1] = "orange";
		strings[2] = "mango";
		strings[3] = "watermelon";
		strings[4] = "melon";
		strings[5] = "apple";
		strings[6] = "fruit";
		strings[7] = "pomegranate";
		strings[8] = "pear";
		strings[9] = "plum";
		strings[10] = "grape";
		strings[11] = "wine";
		strings[12] = "banana";
		strings[13] = "orange";
		strings[14] = "mandarin";
		strings[15] = "apple";
		strings[16] = "coconut";
		strings[17] = "pear";
		strings[18] = "wine";
		strings[19] = "orange";
		
		List<String> uniqueStrings = ArrayParser.findUnique(strings);		
		System.out.println("------------------------------------");
		System.out.println("Unique entries:");
		System.out.println();
		for (int i = 0; i < uniqueStrings.size(); i++) {
			System.out.println(uniqueStrings.get(i));
		}
		System.out.println("------------------------------------");
		
		PhoneDirectory phoneDirectory = new PhoneDirectory();
		phoneDirectory.add("Khanov", "8(910)123-34-33");
		phoneDirectory.add("Ivanov", "8(913)233-32-67");
		phoneDirectory.add("Khanov", "8(910)333-34-33");
		System.out.println("Списки телефонов с запросом по фамилиям:");
		System.out.println("Иванов: " + phoneDirectory.getPhoneNumbers("Ivanov"));
		System.out.println("Ханов: " + phoneDirectory.getPhoneNumbers("Khanov"));
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
	
	private static void doHomeworkCoreProfLesson1() {
		System.out.println("task 1");
		Integer[] intArr = { 4, 5, 6, 2, 1, 9, 10 };
		GenericsTest.arrayElementSwitch(intArr, 0, 6);
		for (Integer integer : intArr) {
			System.out.print(integer + ", ");
		}
		System.out.println();
		String[] stringArr = { "буря", "мглою", "небо", "кроет" };
		GenericsTest.arrayElementSwitch(stringArr, 1, 3);
		for (String string : stringArr) {
			System.out.print(string + " ");
		}
		System.out.println();
		System.out.println("task 2");
		List<Integer> intList = GenericsTest.arrayToArrList(intArr);
		for (Integer integer : intList) {
			System.out.print(integer + ", ");
		}
		System.out.println();
		List<String> stringList = GenericsTest.arrayToArrList(stringArr);
		for (String string : stringList) {
			System.out.print(string + " ");
		}
		System.out.println();
		System.out.println("task 3");
		Box<Apple> appleBox = new Box<>();
		appleBox.addFruit(new Apple(100));
		appleBox.addFruit(new Apple(200));
		appleBox.addFruit(new Apple(300));
		appleBox.addFruit(new Apple(200));
		Box<Orange> orangeBox = new Box<>();
		orangeBox.addFruit(new Orange(200));
		orangeBox.addFruit(new Orange(300));
		orangeBox.addFruit(new Orange(100));
		orangeBox.addFruit(new Orange(200));
		if(appleBox.compare(orangeBox)) {
			System.out.println("Коробки равны по весу");
		} else {
			System.out.println("Коробки отличаются по весу");
		}
		Box<Orange> orangeBox2 = new Box<>();
		orangeBox.moveFruits(orangeBox2);
		System.out.println(orangeBox.toString());
		System.out.println(orangeBox2.toString());
	}



}
