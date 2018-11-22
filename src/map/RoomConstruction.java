package map;

import UI.Images;

public class RoomConstruction extends Room{

	public RoomConstruction(int position, Map map) {
		super(Images.CONSTRUCTIONROOM, position, 5000, 500, map);
		super.setAvailable(false);
	}
}
