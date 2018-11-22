package character;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import controller.GameManager;
import javafx.scene.image.Image;
import map.Map;
import map.MapUpStair;
import map.Room;

public class Visitor extends Npc implements Walkable{
	private int stage;
	private int talkTick;
	private Receptionist contactPerson;
	private Room room;
	
	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 230, 450, 0, 0);
		super.setFacing("UP");
		this.stage = 0;
		this.talkTick = 0;
		this.contactPerson = null;
		this.room = null;
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
		else if(this.stage == 4) {
			this.setActive(true);
			this.walkAround();
		}

	}
	
	private void findContactPerson(){
		for(Npc npc: super.getMap().getNpcList()) {
			if(npc instanceof Receptionist) {
				if(!((Receptionist) npc).isBusy() && hasRoom()) {
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
	
	private void walkToContactPerson(){
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
	
	private void talkWithContactPerson() {
		super.setFacing("RIGHT");
		this.talkTick++;
		if(talkTick == 100) {
			this.stage = 3;
		}	
	}
	
	private void walkToWarpUp() {
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
			this.room.setVisitor(this);
			this.contactPerson.setBusy(false);
			if( this.room.getPosition() < 3 ) {
				this.setPosition(78, 166*(this.room.getPosition()) + 65);
			}else {
				this.setPosition(378, 166*(this.room.getPosition() - 3) + 65);
			}
			this.setVelocity(-1, 0);
			this.setFacing("LEFT");
		}
	}
	
	public void walkAround() {
		if(this.getPositionX() == 30 + 300*((int) room.getPosition()/3)) {
			this.setFacing("RIGHT");
			this.setVelocity(1, 0);
		}
		else if(this.getPositionX() == 150 + 300*((int) room.getPosition()/3)) {
			this.setFacing("LEFT");
			this.setVelocity(-1, 0);
		}
	}
	
	private boolean hasRoom() {
		for(Map map: GameManager.getMaps()) {
			if(map instanceof MapUpStair) {
				for(Room r: ((MapUpStair) map).getRoomsList()) {
					if(r.isAvailable()) {
						r.setAvailable(false);
						this.room = r;
						Player.addMoney(r.getFee());
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void update() {
		walk();
		super.update();
	}
	
	
}
