package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import map.RoomConstruction;

public class MapUpStair extends Map{
	private ArrayList<Room> roomsList;
	private int floor;
	
	public MapUpStair(int floor) {
		super();
		this.setFloor(floor);
		this.roomsList = new ArrayList<Room>();
		initRoom();
		this.setWarpUp(new Rectangle(Images.WARPUP, 216, 0));
		this.setWarpDown(new Rectangle(Images.WARPDOWN, 216, 487));
		this.setBackground(Images.FLOOR);
	}
	
	private void initRoom() {
		for(int i = 0; i < 6; i++) {
			roomsList.add(new RoomConstruction(i, this));
		}
	}

	public ArrayList<Room> getRoomsList() {
		return roomsList;
	}

	public void addRoom(Room o) {
		roomsList.add(o);
	}
	
	public void removeRoom(int position) {
		for(int i = 0; i < 6; i++) {
			if(this.roomsList.get(i).position == position) {
				if(this.roomsList.get(i) instanceof RoomConstruction) {
				}else {
					this.roomsList.set(i, new RoomConstruction(i, this));
					break;
				}
			}
		}
	}
	
	public void setRoom(int position, int level) {
		switch(level) {
			case 0:this.roomsList.set(position, new RoomConstruction(position, this));break;
			case 1:this.roomsList.set(position, new RoomStandard(position, this));break;
			case 2:this.roomsList.set(position, new RoomExecutive(position, this));break;
			case 3:this.roomsList.set(position, new RoomPresidential(position, this));break;
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
		
		for(Room o : roomsList) {
			o.render(gc);
		}
		
		for(Npc o: this.getNpcList()) {
			o.render(gc);
		}
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void clear() {
		for(Room r: roomsList) {
			r.clear();
		}
		this.getNpcList().clear();
		GameManager.setCustomer(0);
	}
	
}
