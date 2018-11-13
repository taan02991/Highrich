package map;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import character.Receptionist;
import character.Visitor;
import javafx.scene.canvas.GraphicsContext;

public class MapWelcome extends Map{
	private static final int maxReceptionist = 4;
	private int numberOfReceptionist;
	
	public MapWelcome(){
		super();
		this.setWarpUp(new Rectangle(Images.WARP, 190, 0));
		this.setBackground(Images.FLOOR);
		this.addStruct(new Rectangle(Images.TREE, 140, 442));
		this.addStruct(new Rectangle(Images.TREE, 288, 442));
		this.addStruct(new Rectangle(Images.SOFA, 431, 118));
		this.addNpc(new Visitor(Images.PLAYERL, Images.PLAYERR, Images.PLAYERU, Images.PLAYERD, this));
	}
	
	public int addReceptionist(int n){
		if(this.numberOfReceptionist == this.maxReceptionist) {
			n = 0;
//			throw new Exception or may be pop up message ("Can't add more receptionist");
		}
		else if(this.numberOfReceptionist + n > this.maxReceptionist) n = this.maxReceptionist - this.numberOfReceptionist;
		for(int i = 0; i < n; i++) {
			double x = 10;
			double y = 100 + this.numberOfReceptionist * 20;
			this.numberOfReceptionist += 1;
			Npc receptionist = new Receptionist(Images.PLAYERL, Images.PLAYERR, Images.PLAYERU, Images.PLAYERD, this, x, y);
			this.getNpcList().add(receptionist);
		}
		//this function return number of receptionists have been added
		return n;
	}
	
}
