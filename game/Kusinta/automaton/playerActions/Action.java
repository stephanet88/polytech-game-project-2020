package playerActions;

public abstract class Action implements IAction {
	
	public float percentage;
	
	public Action() {
		percentage = -1;
	}
	
	public Action(int percentage) {
		this.percentage = (float) percentage;
	}

}
