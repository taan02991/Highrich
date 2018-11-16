package map;

import UI.Images;

public class RoomConstruction extends Room{

	public RoomConstruction(int position) {
		super(Images.CONSTRUCTIONROOM, position, 5000, 500);
		super.setAvailable(false);
	}
}
