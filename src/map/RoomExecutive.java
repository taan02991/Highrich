package map;

import UI.Images;

public class RoomExecutive extends Room{

	public RoomExecutive(int position, Map map) {
		super(Images.EXECUTIVEROOM, position, 20000, 2000, map);
		super.setAvailable(true);
	}

}
