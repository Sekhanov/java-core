package homeworkLesson3;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser {
	
	public static ArrayList<String> findUnique(String[] arr) {
		ArrayList<String> uniqueStrings = new ArrayList<>();
		
		uniqueStrings.add(arr[0]);		
		for (String arrString : arr) {
			int hash = arrString.hashCode();
			boolean isDuplicate = false;
			for (String listString : uniqueStrings) {
				isDuplicate |= hash == listString.hashCode();				
				}
			if(!isDuplicate) {
				uniqueStrings.add(arrString);
			}
		}
		
		stringRepeatCount(arr, uniqueStrings);
		
		return uniqueStrings;
		
	}
	
	private static void stringRepeatCount(String[] arrStrings, List<String> unieuqStrings) {
		for (String uniqueString : unieuqStrings) {
			int cont = 0;
			for (String arrString : arrStrings) {
				if(uniqueString.hashCode()  == arrString.hashCode()) {
					cont ++;
				}
			}
			System.out.println("String \"" + uniqueString + "\"repeats " + cont + " times");
		}
	}
	
}
