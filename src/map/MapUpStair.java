package map;

import UI.Images;
import UI.Rectangle;

public class MapUpStair extends Map {
	public MapUpStair() {
		super();
		this.warpUp = new Rectangle(Images.warp, 190, 0);
		this.warpDown = new Rectangle(Images.warp, 190, 480);
		this.setBackground(Images.floor);
	}
}
