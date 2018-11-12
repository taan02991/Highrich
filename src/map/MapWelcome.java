package map;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import character.Receptionist;
import javafx.scene.canvas.GraphicsContext;

public class MapWelcome extends Map{
	private static final int maxNumberOfReceptionist = 4;
	private int numberOfReceptionist;
	
	public MapWelcome(){
		super();
		this.warpUp = new Rectangle(Images.warp, 190, 0);
		this.setBackground(Images.floor);
		this.addStruct(new Rectangle(Images.tree, 140, 442));
		this.addStruct(new Rectangle(Images.tree, 288, 442));
		this.addStruct(new Rectangle(Images.sofa, 431, 118));
		this.addNpc(new Npc(Images.playerD, this, 0, 0, 1, 0));
	}
	
	public int addReceptionist(int n){
		if(this.numberOfReceptionist == this.maxNumberOfReceptionist) {
			n = 0;
//			throw new Exception("Can't add more receptionist");
		}
		else if(this.numberOfReceptionist + n > this.maxNumberOfReceptionist) n = this.maxNumberOfReceptionist - this.numberOfReceptionist;
		this.numberOfReceptionist += n;
		for(int i = 0; i < n; i++) {
			double x = 10;
			double y = 10 + this.numberOfReceptionist * 100;
			Npc receptionist = new Receptionist(Images.playerR, this, x, y);
			npcList.add(receptionist);
		}
		return n;
	}
	
}
