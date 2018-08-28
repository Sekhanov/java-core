package homeworkLesson2;

/**
 * Исключение выбрасывается, когда размер массива превышает ограничения заданные в методе класса MyArray
 */
public class MyArraySizeException extends Exception {
	

	public MyArraySizeException(String string) {
		super(string);
	}
	
}
 