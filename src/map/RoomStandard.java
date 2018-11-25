package map;

import UI.Images;
import UI.Rectangle;

public class RoomStandard extends Room{

	public RoomStandard(int position, Map map) {
		super(Images.STANDARDROOM, position, 10000, 1000, map);
		
		if(((MapUpStair) map).getRoomsList().get(position) instanceof RoomConstruction) {
			super.setAvailable(true);			
		}
		else {
			super.setAvailable(this.isAvailable);
		}
		
		if(position < 3) {
			super.addRoomStruct(new Rectangle(Images.VERTICALWALL, 0, 166*(this.position)));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 197, 166*(this.position)+2));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 197, 166*(this.position)+104));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 4, 166*(this.position)));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 4, 166*(this.position)+164));
			
			super.addRoomStruct(new Rectangle(Images.TOILETLEFT, 4, 166*(this.position) + 2));
			super.addRoomStruct(new Rectangle(Images.BEDLEFT, 4, 166*(this.position) + 101));
		}else{
			super.addRoomStruct(new Rectangle(Images.VERTICALWALL, 496, 166*(this.position-3)));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 300, 166*(this.position-3)+2));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 300, 166*(this.position-3)+104));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 300, 166*(this.position-3)));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 300, 166*(this.position-3)+164));
			
			super.addRoomStruct(new Rectangle(Images.TOILETRIGHT, 426, 166*(this.position-3) + 2));
			super.addRoomStruct(new Rectangle(Images.BEDRIGHT, 426, 166*(this.position-3) + 101));
		}
	}

}
