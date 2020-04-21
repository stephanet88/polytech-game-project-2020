package playerCond;

import game.Category;
import game.Direction;
import game.Entity;

public class CondClosest implements ICondition{

	Direction direction;
	Category category;		//A remplacer par un autre champ si besoin
	
	
	public CondClosest(Category category, Direction direction) {
		this.category = category;
		this.direction = direction;
	}

	@Override
	public boolean eval(Entity e) {
		return e.closest(category, direction);
	}
	
	
}