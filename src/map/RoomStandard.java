package map;

import UI.Images;

public class RoomStandard extends Room{

	public RoomStandard(int position) {
		super(Images.STANDARDROOM, position, 10000, 1000);
		super.setAvailable(true);
	}

}
