package map;

import UI.Images;

public class RoomStandard extends Room{

	public RoomStandard(int position, Map map) {
		super(Images.STANDARDROOM, position, 10000, 1000, map);
		super.setAvailable(true);
	}

}
