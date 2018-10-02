package coreProfHwLesson1;

import java.util.ArrayList;

public class GenericsTest {

	public static<T> void arrayElementSwitch(T[] arr, int index1, int index2) {
		T element1 = arr[index1];
		T element2 = arr[index2];
		arr[index1] = element2;
		arr[index2] = element1;
	}
	
	public static<T> ArrayList<T> arrayToArrList(T[] arr) {
		ArrayList<T> resultList = new ArrayList<>();
		for (T t : arr) {
			resultList.add(t); 
		}
		return resultList;
	}
}
