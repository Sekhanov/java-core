package coreProfHwLesson4;

import java.util.Arrays;
import java.util.List;

public class Controller {

	private int position = 0;
	private int count;
	private List<String> simbols;
	
	public Controller(int count, String... simbols) {
		this.count = count;
		this.simbols = Arrays.asList(simbols);
	}
	
	public void move() {
		position = (position == simbols.size() - 1) ? 0 : position + 1;
	}
	
	public String getCurrent() {
		return simbols.get(position);
	}
	
	public void start() {
		simbols.forEach(simbol -> new NamedThred(simbol, count, this).start());
	}
	
	
	
}
