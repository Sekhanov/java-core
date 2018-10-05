package coreProfHwLesson4;

public class Monitor {
	
	private boolean blockA = false;
	private boolean blockB = true;
	private boolean blockC = true;
	
	public boolean isBlockA() {
		return blockA;
	}
	public void setBlockA(boolean blockA) {
		this.blockA = blockA;
	}
	public boolean isBlockB() {
		return blockB;
	}
	public void setBlockB(boolean blockB) {
		this.blockB = blockB;
	}
	public boolean isBlockC() {
		return blockC;
	}
	public void setBlockC(boolean blockC) {
		this.blockC = blockC;
	}
	
}
