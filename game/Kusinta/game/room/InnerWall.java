package room;

import environnement.Element;
import game.Coord;
import game.ImageLoader;

/*
 * 
 * Dans le cas d'un InnerWall, les images sont dans un tableau de String
 * 
 */

public class InnerWall extends Element {
	
	public InnerWall(Coord coord, InnerWallImageManager IWImageManager) throws Exception {
		super(true, true, coord);
		String path = IWImageManager.get("");
		if (path != null) {
			__image = ImageLoader.loadImage(path, SIZE);
		}
	}


}
