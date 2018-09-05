package homeworkLesson5;

import java.util.function.Consumer;

public class ConcurrencyTest {
	
	public float[] fillFloatArr(float[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 1;
		}
		return arr;
	}

	public void oneThreadProcessArray(float[] arr) {			
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
		}

	}
	
	public void twoThreadProcessArray(float[] arr) {		
		int halfArrSize = arr.length / 2;
		float[] arr1 = new float[halfArrSize];
		float[] arr2 = new float[halfArrSize];		
		System.arraycopy(arr, 0, arr1, 0, halfArrSize);
		System.arraycopy(arr, halfArrSize, arr2, 0, halfArrSize);		
		Thread thread2 = new Thread(() -> oneThreadProcessArray(arr2), "secondThread");
		thread2.start();
		oneThreadProcessArray(arr1);
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.arraycopy(arr1, 0, arr, 0, halfArrSize);
		System.arraycopy(arr1, 0, arr, halfArrSize, halfArrSize);
	}
	
	public<T> long processTime(Consumer<T> c, T arr) {
		long startTime = System.currentTimeMillis();
		c.accept(arr);		
		return System.currentTimeMillis() - startTime;
	}

}
