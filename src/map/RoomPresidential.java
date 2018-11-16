package map;

import UI.Images;

public class RoomPresidential extends Room{

	public RoomPresidential(int position) {
		super(Images.PRESIDENTIALROOM, position, 0, 0);
		super.setAvailable(true);
	}

}
