package playerActions;

import game.Entity;

public class ActionGet extends Action {
	
	public ActionGet(int percentage) {
		super(percentage);
	}

	@Override
	public boolean apply(Entity e) {
		return e.get();
	}

}
