package character;

import javafx.scene.image.Image;
import map.Map;

public class Visitor extends Npc implements Walkable{
	private int stage;
	private int talkTick;
	private Receptionist contactPerson;
	
	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 230, 450, 0, 0);
		super.setFacing("UP");
		this.stage = 0;
		this.talkTick = 0;
		this.contactPerson = null;
	}
	
	@Override
	public void walk() {
		if(this.stage == 0) {
			this.findContactPerson();
		}
		else if(this.stage == 1) {
			this.walkToContactPerson();
		}
		else if(this.stage == 2) {
			this.talkWithContactPerson();;
		}
		else if(this.stage == 3) {
			this.walkToWarpUp();
		}

	}
	
	public void findContactPerson(){
		for(Npc npc: super.getMap().getNpcList()) {
			if(npc instanceof Receptionist) {
				if(!((Receptionist) npc).isBusy()) {
					this.contactPerson = (Receptionist) npc;
					this.contactPerson.setBusy(true);
					stage = 1;
					return;
				}
			}
		}
		if(super.getPositionY() != 450) {
			super.setVelocity(0, 1);
			super.setFacing("DOWN");
		}
		else {
			super.setActive(false);
		}
	}
	
	public void walkToContactPerson(){
		if(this.contactPerson != null) {
			if(this.contactPerson.getPositionY() != this.getPositionY()) {
				super.setVelocity(0, -1);
				super.setFacing("UP");
			}
			else if(this.contactPerson.getPositionX() + 50 != this.getPositionX()) {
				super.setVelocity(-1, 0);
				super.setFacing("LEFT");
			}
			else {
				super.setVelocity(0, 0);
				this.stage = 2;
			}
		}
		
	}
	
	public void talkWithContactPerson() {
		super.setFacing("RIGHT");
		this.talkTick++;
		if(talkTick == 1000) {
			this.contactPerson.setBusy(false);
			this.stage = 3;
		}	
	}
	
	public void walkToWarpUp() {
		if(super.getPositionX() != 250) {
			super.setVelocity(1, 0);
			super.setFacing("RIGHT");
		}
		else if(super.getPositionY() != 50) {
			super.setVelocity(0, -1);
			super.setFacing("UP");
		}
		else {
			this.stage = 4;
			super.setActive(false);
		}
	}
	
	@Override
	public void update() {
		walk();
		super.update();
	}
	
	
}
