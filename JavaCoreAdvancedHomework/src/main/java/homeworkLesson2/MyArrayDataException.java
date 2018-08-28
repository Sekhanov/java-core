package homeworkLesson2;


/**
 * Исключение расширяет действие NumberFormatException для класса MyArray, позволяя использовать
 * индексы ячеек двумерного массива, в которой выброшено исключение
 */
public class MyArrayDataException extends Exception {
	
	private int firsDemensionArrayCell;
	private int secondDemendionArrayCell;


	public MyArrayDataException(String string, int firsDemensionArrayCell, int secondDemendionArrayCell) {
		super(string);
		this.firsDemensionArrayCell = firsDemensionArrayCell;
		this.secondDemendionArrayCell = secondDemendionArrayCell;
		
	}
	
	public int getFirsDemensionArrayCell() {
		return firsDemensionArrayCell;
	}
	
	public int getSecondDemendionArrayCell() {
		return secondDemendionArrayCell;
	}


	
	
}
