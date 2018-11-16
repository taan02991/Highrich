package map;

import UI.Images;

public class RoomExecutive extends Room{

	public RoomExecutive(int position) {
		super(Images.EXECUTIVEROOM, position, 20000, 2000);
		super.setAvailable(true);
	}

}
