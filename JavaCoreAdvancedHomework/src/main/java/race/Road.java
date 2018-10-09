package race;

public class Road extends Stage{

	public Road(int length) {
		this.lenght = length;
		this.descripiton = "дорога длиной " + length + "метров"; 
	}
	
	@Override
	public void go(Car car) {
		try {
			System.out.println(car.getName() + " начал этап " + descripiton);
			Thread.sleep(lenght / car.getSpeed() * 1000);
			System.out.println(car.getName() + " закончил этап " + descripiton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
