package map;

import UI.Rectangle;

public class Room {
	protected boolean isAvailable;
	protected int position;
	
	public Room(int position) {
		this.isAvailable = true;
		this.position = position;
	}
}
