package homeworkLesson2;

/**
 * Исключение выбрасывается, когда размер массива превышает ограничения заданные в методе класса MyArray
 */
public class MyArraySizeException extends Exception {
	
	private static final long serialVersionUID = 899727428497236153L;

	public MyArraySizeException(String string) {
		super(string);
	}
	
}
 