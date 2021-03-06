package playerCond;


import automaton.Category;
import automaton.Direction;
import automaton.Entity;

public class CondCell implements ICondition{
	
	Direction direction;
	Category category;				//A modifier par un autre champ si besoin

	public CondCell(Direction direction, Category category) {
		this.direction = direction;
		this.category = category;
	}

	@Override
	public boolean eval(Entity e) {
		return e.cell(direction, category);
	}
	
}
