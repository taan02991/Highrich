package map;

import UI.Images;

public class RoomPresidential extends Room{

	public RoomPresidential(int position, Map map) {
		super(Images.PRESIDENTIALROOM, position, 0, 0, map);
		super.setAvailable(true);
	}

}
