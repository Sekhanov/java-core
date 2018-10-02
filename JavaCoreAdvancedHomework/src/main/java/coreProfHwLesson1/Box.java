package coreProfHwLesson1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<T extends Fruit> {

	private List<T> fruits;
	
	public Box() {
		fruits = new ArrayList<>();
	}
	
	public void addFruit(T fruit) {
		fruits.add(fruit);
	}
	
	public int getWeight() {
		int result = 0;
		for (T t : fruits) {
			result += t.getWeight();
		}			
		return result;
	}
	
	public boolean compare(Box<? extends Fruit> box) {
		return this.getWeight() == box.getWeight() ? true : false;
	}
	
	public void moveFruits(Box<T> box) {
		Iterator<T> iterator =  this.fruits.iterator();
		while(iterator.hasNext()) {
			box.addFruit(iterator.next());
			iterator.remove();
		}
	}

	@Override
	public String toString() {
		String result = "Коробка с фруктами: ";
		if(fruits.size() > 0) {
			for (T t : fruits) {
				result += t.toString() + " ";
			}
		} else {
			result += "пуста";
		}
		return result;

	}
	
		
}
