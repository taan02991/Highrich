package character;

import UI.Images;
import controller.GameManager;
import javafx.scene.image.Image;
import map.Map;
import map.MapUpStair;
import map.Room;

public class Visitor extends Npc implements Walkable{
	protected int state;
	protected int talkTick;
	protected Receptionist contactPerson;
	protected Room room;
	
	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 280, 468, 0, 0);
		super.setFacing("UP");
		this.state = 0;
		this.talkTick = 0;
		this.contactPerson = null;
		this.room = null;
	}
	
	@Override
	public void walk() {
		if(this.state == 0) {
			this.findContactPerson();
		}
		else if(this.state == 1) {
			this.walkToContactPerson();
		}
		else if(this.state == 2) {
			this.talkWithContactPerson();
		}
		else if(this.state == 3) {
			this.walkToWarpUp();
		}
		else if(this.state == 4) {
			this.setActive(true);
			this.walkAround();
		}
		else if(this.state == -1) {
			this.Upset();
		}
		else if(this.state == -2) {
			this.walkOut();
		}

	}
	
	public void findContactPerson(){
		for(Npc npc: super.getMap().getNpcList()) {
			if(npc instanceof Receptionist) {
				if(!((Receptionist) npc).isBusy() && hasRoom()) {
					this.contactPerson = (Receptionist) npc;
					this.contactPerson.setBusy(true);
					state = 1;
					return;
				}
			}
		}
		state = -1;
	}
	
	public void walkToContactPerson(){
		if(this.contactPerson != null) {
			if(this.contactPerson.getPositionY() != this.getPositionY()) {
				super.setVelocity(0, -1);
				super.setFacing("UP");
			}
			else if(this.contactPerson.getPositionX() + 90 != this.getPositionX()) {
				super.setVelocity(-1, 0);
				super.setFacing("LEFT");
			}
			else {
				super.setVelocity(0, 0);
				state = 2;
			}
		}
		
	}
	
	public void talkWithContactPerson() {
		this.talkTick++;
		if(talkTick == 100) {
			this.state = 3;
			GameManager.setCustomer(GameManager.getCustomer() + 1);
			GameManager.setAvailableRoom(GameManager.getAvailableRoom() - 1);
			GameManager.addPopularity();
			GameManager.getPlayer().addMoney(this.room.getFee());
		}	
	}
	
	public void walkToWarpUp() {
		if(super.getPositionX() != 204) {
			super.setVelocity(1, 0);
			super.setFacing("RIGHT");
		}
		else if(super.getPositionY() != 0) {
			super.setVelocity(0, -1);
			super.setFacing("UP");
		}
		else {
			this.state = 4;
			this.contactPerson.setBusy(false);
			super.setActive(false);
			this.room.setVisitor(this);
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
		if(this.getPositionX() == 36 + 300*((int) room.getPosition()/3)) {
			this.setFacing("RIGHT");
			this.setVelocity(1, 0);
		}
		else if(this.getPositionX() == 132 + 300*((int) room.getPosition()/3)) {
			this.setFacing("LEFT");
			this.setVelocity(-1, 0);
		}
	}
	
	public void Upset() {
		if(this.getPositionY() != 400) {
			this.setVelocity(0, -1);
			this.setFacing("UP");
		}
		else if(this.getPositionX() != 210) {
			this.setVelocity(-1, 0);
			this.setFacing("LEFT");
		}
		else {
			this.setVelocity(0, 0);
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						state = -9999;
						showMessage(Images.UPSET, 500);
						Thread.sleep(500);
						state = -2;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
			t.start();
		}
	}
	
	public void walkOut() {
		if(this.getPositionY() != 458) {
			this.setVelocity(0, 1);
			this.setFacing("DOWN");
		}
		else {
			GameManager.minusPopularity();
			this.setActive(false);
		}
	}
	
	public boolean hasRoom() {
		for(Map map: GameManager.getMaps()) {
			if(map instanceof MapUpStair) {
				for(Room r: ((MapUpStair) map).getRoomsList()) {
					if(r.isAvailable()) {
						r.setAvailable(false);
						this.room = r;
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
