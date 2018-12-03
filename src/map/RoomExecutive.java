package map;

import UI.Images;
import UI.Rectangle;
import controller.GameManager;

public class RoomExecutive extends Room{

	public RoomExecutive(int position, Map map) {
		super(Images.EXECUTIVEROOM, position, 20000, 2000, map);	
		Room oldRoom = ((MapUpStair) map).getRoomsList().get(position);
		super.setAvailable(oldRoom.isAvailable);
		if(oldRoom.getVisitor() != null) {
			super.setVisitor(oldRoom.getVisitor());
		}
		GameManager.setnStandard(GameManager.getnStandard() - 1);
		GameManager.setnExecutive(GameManager.getnExecutive() + 1);
		
		if(position < 3) {
			super.addRoomStruct(new Rectangle(Images.VERTICALWALL, 0, 166*(this.position)));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 197, 166*(this.position)+2));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 197, 166*(this.position)+104));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 4, 166*(this.position)));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 4, 166*(this.position)+164));
			
			super.addRoomStruct(new Rectangle(Images.TOILETLEFT, 4, 166*(this.position) + 2));
			super.addRoomStruct(new Rectangle(Images.BEDLEFT, 4, 166*(this.position) + 101));
			super.addRoomStruct(new Rectangle(Images.TVLEFT, 87, 166*(this.position) + 115));
		}else{
			super.addRoomStruct(new Rectangle(Images.VERTICALWALL, 496, 166*(this.position-3)));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 300, 166*(this.position-3)+2));
			super.addRoomStruct(new Rectangle(Images.VERTICALWALLONDOOR, 300, 166*(this.position-3)+104));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 300, 166*(this.position-3)));
			super.addRoomStruct(new Rectangle(Images.HORIZONTALWALL, 300, 166*(this.position-3)+164));
			
			super.addRoomStruct(new Rectangle(Images.TOILETRIGHT, 426, 166*(this.position-3) + 2));
			super.addRoomStruct(new Rectangle(Images.BEDRIGHT, 426, 166*(this.position-3) + 101));
			super.addRoomStruct(new Rectangle(Images.TVRIGHT, 315, 166*(this.position-3) + 115));
		}
	}

}
