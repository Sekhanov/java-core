package coreProfHwLesson6;

import java.util.Arrays;

public class ArrayUtils {

	public int[] newArrayFormFour(int[] arr) {	
		int f = -1;		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == 4) f = i;
		}
		if(f == -1) {
			throw new RuntimeException();
		}
		return Arrays.copyOfRange(arr, f + 1, arr.length);
	}
	
	public boolean checkEqualNumbers(int[] arr) {
		int oneCount = 0;
		int fourCount = 0;
		for (int i : arr) {
			if(i != 1 && i !=4) throw new IllegalArgumentException();
			if(i == 1) oneCount++;
			if(i == 4) fourCount++;
		}
		return oneCount == fourCount ? true : false;
	}
}
