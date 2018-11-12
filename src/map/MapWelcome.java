package map;

import UI.Images;
import UI.Rectangle;
import character.Npc;

public class MapWelcome extends Map{
	
	public MapWelcome(){
		super();
		this.warpUp = new Rectangle(Images.warp, 190, 0);
		this.setBackground(Images.floor);
		this.addStruct(new Rectangle(Images.tree, 140, 442));
		this.addStruct(new Rectangle(Images.tree, 288, 442));
		this.addStruct(new Rectangle(Images.sofa, 431, 118));
		this.addNpc(new Npc(Images.playerD, this, 0, 0, 1, 0));
	}
	
	
}
