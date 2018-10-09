package race;

import lombok.Getter;

public abstract class Stage {
	
	protected int lenght;
	
	@Getter
	protected String descripiton;
	
	public abstract void go(Car car);
	

}
