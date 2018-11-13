package character;

import javafx.scene.image.Image;
import map.Map;

public class Visitor extends Npc implements Walkable{
	private double targetX;
	private double targetY;
	private int stage;
	private boolean taskComplete;
	
	public Visitor(Image[] npcL, Image[] npcR, Image[] npcU, Image[] npcD, Map map) {
		super(npcL, npcR, npcU, npcD, map, 250, 480, 0, 0);
		super.setFacing("UP");
		this.stage = 0;
		this.taskComplete = true;
	}
	
	@Override
	public void walk() {
		if(this.stage == 0) {
			this.walkToReception();
			this.taskComplete = false;
		}
		else if(stage == 1) {
			this.talkWithReceptionist();
			this.taskComplete = false;
		}
		else if(stage == 2) {
			this.walkToWarpUp();
			this.taskComplete = false;
		}
	}
	
	public boolean walkToReception() {
		for(Npc npc: super.getMap().getNpcList()) {
			if(npc instanceof Receptionist) {
				Receptionist receptionist = (Receptionist) npc;
				if(!receptionist.isBusy()) {
					this.targetX = receptionist.getPositionX() + 25;
					this.targetY = receptionist.getPositionY();
					receptionist.setBusy(true);
					return true;
				}
			}
		}
		return false;
		
	}
	
	public void talkWithReceptionist() {
		
	}
	
	public void walkToWarpUp() {
		this.targetX = 0;
		this.targetY = 250;
		super.getMap().getNpcList().remove(this);
	}
	
	public void walkTo(double targetX, double targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.setVelocity(1, 1);
		if(this.targetX == super.getVelocityX() && this.targetY == super.getPositionY()) {
			this.setVelocity(0, 0);
			this.stage++;
			this.taskComplete = true;
		}
		if(this.targetY != super.getPositionY()) {
			if(this.targetY - super.getPositionY() < 0) this.setVelocity(0, -1);
			else this.setVelocity(0, 1);
		}
		else if(this.targetX != super.getPositionX()) {
			if(this.targetX - super.getPositionX() < 0) this.setVelocity(-1, 0);
			else this.setVelocity(1, 0);
		}
	}

	public void update() {
		if(taskComplete) {
			this.walk();
		}
		this.walkTo(targetX, targetY);
		super.update();
	}
}
