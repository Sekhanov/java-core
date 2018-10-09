package race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

public class Race {
	

	private CountDownLatch startRaceCountDownLatch;	
	private CyclicBarrier finishCyclicBarrier;	
	private AtomicInteger result;
	
	@Getter
	private List<Stage> stages;	
	private List<Car> cars;
	
	public Race(Stage...stages) {
		this.stages = Arrays.asList(stages);
		cars = new ArrayList<>();
		System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
		
	}
	
	public void prepareRace(String name) {
			System.out.println("Участник " + name + " готовится к гонке");
		try {
			Thread.sleep(300 + (int) (Math.random() * 1000));
			System.out.println("Участник " + name + " готов");
			startRaceCountDownLatch.countDown();
			startRaceCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}

	public void finishRace(String name) {
		int position = result.getAndIncrement();
		System.out.println(position == 1 ? name + " WIN!!!" : name + " занял " + position + " место");
		try {
			finishCyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public void startRace() {
		startRaceCountDownLatch = new CountDownLatch(cars.size());
		finishCyclicBarrier = new CyclicBarrier(cars.size(), () -> System.out.println("Важное объявление >>>> Гонка закончилась!!!"));
		result = new AtomicInteger(0);
//		cars.forEach(Thread::start);
		cars.forEach(car -> new Thread(car).start());
		try {
			startRaceCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Важное объявление >>> Гонка началась!!!");
		
	}
	
	
	
}
