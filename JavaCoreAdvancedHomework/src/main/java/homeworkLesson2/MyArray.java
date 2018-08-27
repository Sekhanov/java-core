package homeworkLesson2;

public class MyArray {
	
	private static final int MAX_ARRAY_LENGHT = 3;
	
	
	/**
	 * 
	 * @param stringArr - двумерный массив с максимальной размерностью определенной в константе MAX_ARRAY_LENGHT
	 * @throws MyArraySizeException, MyArrayDataException
	 */
	public int processArray (String[][] stringArr) throws MyArraySizeException, MyArrayDataException {
		if(stringArr.length > MAX_ARRAY_LENGHT) {
			throw new MyArraySizeException("Размер массива первого порядка двумерного массива превышает максимальное значение на" + (stringArr.length - MAX_ARRAY_LENGHT));
		}
		for (int i = 0; i < stringArr.length; i++) {
			if(stringArr[i].length > MAX_ARRAY_LENGHT) {
				throw new MyArraySizeException("Размер массива второго порядка двумерного массива превышает максимальное значение на "+ (stringArr[i].length - MAX_ARRAY_LENGHT));
			}
		}
		
		int[][] intArr = new int[MAX_ARRAY_LENGHT][MAX_ARRAY_LENGHT];
		int summ = 0;
		for (int i = 0; i < stringArr.length; i++) {
			for (int j = 0; j < stringArr[i].length; j++) {
				try {
					intArr[i][j] = Integer.parseInt(stringArr[i][j]);
					summ += intArr[i][j];
				} 
				catch (NumberFormatException e) {
					String message = String.format("Символ \"%s\" в ячейке [%d][%d] двумерно массива  невозможно преобразовать в число", stringArr[i][j], i, j);
					throw new MyArrayDataException(message, i, j);
				}
			}
		}
		
		return summ;
	}
}
