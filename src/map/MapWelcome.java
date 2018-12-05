package map;

import UI.Images;
import UI.Rectangle;
import character.Adult;
import character.God;
import character.Npc;
import character.OldMan;
import character.ProgMeth;
import character.Receptionist;
import character.Teenager;
import exception.ReceptionistFullException;
import javafx.scene.canvas.GraphicsContext;

public class MapWelcome extends Map{
	private static final int MAXRECEPTIONIST = 8;
	private int numberOfReceptionist;
	
	public MapWelcome(){
		super();
		super.setWarpUp(new Rectangle(Images.WARPUP, 216, 0));
		super.setBackground(Images.FLOOR);
		super.addStruct(new Rectangle(Images.TREE, 125, 442));
		super.addStruct(new Rectangle(Images.TREE, 325, 442));
		super.addStruct(new Rectangle(Images.SOFA, 431, 118));
		super.addStruct(new Rectangle(Images.RECEPTIONTABLE, 54, 92));
	}
	
	public void addProgMeth() {
		for(Npc npc: super.getNpcList()) {
			if(npc instanceof ProgMeth) {
				return;
			}
		}
		super.getNpcList().add(new ProgMeth());			
	}
	
	public void addVisitor() {
		int random = (int) (Math.random()*1000);
		if(random < 80) {
			super.getNpcList().add(new God(this));
		}
		else if(random % 3 == 0) {
			super.getNpcList().add(new Teenager(this));			
		}
		else if(random % 3 == 1) {
			super.getNpcList().add(new Adult(this));
		}
		else{
			super.getNpcList().add(new OldMan(this));
		}
	}
	
	
	public void addReceptionist() throws ReceptionistFullException{
		if(this.numberOfReceptionist == MAXRECEPTIONIST) {
			throw new ReceptionistFullException();
		}
		double x = 20;
		double y = 250;
		this.numberOfReceptionist += 1;
		if(this.numberOfReceptionist%2 == 0) {
			y = 200 + this.numberOfReceptionist/2 * 32;				
		}
		else{
			y = 200 - this.numberOfReceptionist/2 * 32;				
		}
		Npc receptionist = new Receptionist(Images.Receptionist, this, x, y);
		super.getNpcList().add(receptionist);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		super.getWarpUp().render(gc);
		for(Rectangle r: super.getStructList()) {
			r.render(gc);
		}
		for(Npc npc: super.getNpcList()) {
			npc.render(gc);
		}
		
	}

	public int getNumberOfReceptionist() {
		return numberOfReceptionist;
	}
	
	
}
