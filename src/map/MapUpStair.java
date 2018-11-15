package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import javafx.scene.canvas.GraphicsContext;
import map.RoomConstruction;

public class MapUpStair extends Map{
	private ArrayList<Room> roomsList;
	private int floor;
	
	public MapUpStair(int floor) {
		super();
		this.floor = floor;
		this.roomsList = new ArrayList<Room>();
		initRoom();
		this.setWarpUp(new Rectangle(Images.WARP, 190, 0));
		this.setWarpDown(new Rectangle(Images.WARP, 190, 480));
		this.setBackground(Images.FLOOR);
	}
	
	private void initRoom() {
		for(int i = 0; i < 6; i++) {
			roomsList.add(new RoomConstruction(i));
		}
	}

	public ArrayList<Room> getRoomsList() {
		return roomsList;
	}

	public void addRoom(Room o) {
/* not sure		if(roomsList.size() == 6) throw new Exception("Full Room"); */
		roomsList.add(o);
	}
	
	public void removeRoom(int position) {
//ต้องเช็คว่ามีลูกค้าป่าวด้ย, เป็นห้องที่ก่อสร้างแล้ว
		for(int i = 0; i < 6; i++) {
			if(this.roomsList.get(i).position == position) {
				if(this.roomsList.get(i) instanceof RoomConstruction) {
/* not sure 	throw new Exception("This room is aleady construction"); */
				}else {
					this.roomsList.set(i, new RoomConstruction(i));
					break;
				}
			}
		}
	}
	
	public void setRoom(int position, int level) {
		//ขาดเช็คว่ามีลูกค้าป่าว, เป็นห้องที่ต้องยังไม่ก่อสร้าง
		switch(level) {
		case 0:this.roomsList.set(position, new RoomConstruction(position));break;
		case 1:this.roomsList.set(position, new RoomStandard(position));break;
		case 2:this.roomsList.set(position, new RoomExecutive(position));break;
		case 3:this.roomsList.set(position, new RoomPresidential(position));break;
		}
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		super.getWarpUp().render(gc);
		super.getWarpDown().render(gc);
		for(Rectangle o: super.getStructList()) {
			o.render(gc);
		}
		for(Npc o: this.getNpcList()) {
			o.render(gc);
		}
		for(Room o : roomsList) {
			o.render(gc);
		}
	}
	
}
