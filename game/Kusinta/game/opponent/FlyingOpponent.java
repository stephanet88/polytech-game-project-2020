package opponent;

import java.awt.Graphics;
import java.io.IOException;

import automaton.Automaton;
import automaton.Direction;
import game.Model;

public class FlyingOpponent extends Opponent {

	public FlyingOpponent(Automaton automaton, int x, int y, Direction dir, Model model, int maxLife, int life,
			int attackSpeed, int resistance, int strength) throws IOException {
		super(automaton, x, y, dir, model, maxLife, life, attackSpeed, resistance, strength);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics gp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPressed(int keyChar, boolean b) {
		// TODO Auto-generated method stub
		
	}

}
