package room;

import automaton.Automaton;
import environnement.Decor;
import game.Coord;

public class Library extends Decor {
	public Library(Coord coord, Room room, Automaton automaton) {
		super(false,true, false, coord, room, automaton);
		int n = (int) (Math.random() * 2) + 1;
		String image_path = "resources/Room/decors/library" + n + ".png";
		try {
			super.loadImage(image_path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
	}
}
