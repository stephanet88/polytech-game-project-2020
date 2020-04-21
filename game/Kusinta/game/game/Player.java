package game;

import automaton.*;

public class Player extends Entity {
	
	int MAX_LIFE = 100;
	int SPEED_WALK = 5;
	
	 int m_x, m_y;
	 Model m_model;
	 Direction m_direction;
	 
	 int life;

	 double SPEED_WALK_x, SPEED_WALK_y;

	 int [] x_hitBox, y_hitBox;
	 
	 public Player() {}


	public Player(Automaton automaton, int x, int y, Direction dir) {
		super(automaton);
		
		 m_x = x;
		 m_y = y;
		 m_direction = dir;
		 
		 life = MAX_LIFE;

		 x_hitBox = new int[]{m_x-5, m_x-5, m_x+5, m_x+5};
		 y_hitBox = new int[]{m_y-5, m_y+5, m_y+5, m_y-5};
		 
		}

	@Override
	public boolean move(Direction dir) {
		if(dir != m_direction) {
			turn(dir);
		}
		if(dir.toString() == "East") {
			int dx = m_x + SPEED_WALK;
			
		} else if(dir.toString() == "West") {
			int dx = m_x - SPEED_WALK;
		}
		return true;
	}

	@Override
	public boolean jump(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pop(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wizz(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turn(Direction dir) {
		if(m_direction !=dir)
			m_direction = dir;
		return true;
	}

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean explode() {
		if(life!=0) {
			life = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean egg(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mydir(Direction m_dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell(Direction direction, Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(Category category, Direction direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotstuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotpower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean key(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

}