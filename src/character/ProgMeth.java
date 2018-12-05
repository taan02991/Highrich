package character;

import UI.Images;
import controller.GameManager;

public class ProgMeth extends Npc implements Walkable{
	
	private int stage;

	public ProgMeth() {
		super(Images.PROGMETHL, Images.PROGMETHR, Images.PROGMETHU, Images.PROGMETHD, GameManager.getMaps().get(0), 178, 468, 0, 0);
		super.setFacing("UP");
		this.stage = 0;
	}

	@Override
	public void walk() {
		if(this.stage == 0) {
			this.walktoCounter();
		}
		
		else if(this.stage == 1) {
			this.walkOut();
		}
		
		
	}
	
	public void walktoCounter() {
		if(super.getPositionY() != 380) {
			super.setVelocity(0, -1);
			super.setFacing("UP");
		}
		else if(super.getPositionX() != 20){
			super.setVelocity(-1, 0);
			super.setFacing("LEFT");			
		}
		else {
			super.setVelocity(0, 0);
			super.setFacing("UP");
			Thread t = new Thread(() -> {
				try {
					this.stage = -9999;
					super.showMessage(Images.GIVEMEMONEY, 2000);
					Thread.sleep(2000);
					GameManager.getPlayer();
					GameManager.getPlayer();
					GameManager.getPlayer().setMoney(GameManager.getPlayer().getMoney()/2);;
					this.stage = 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			t.start();	
		}
	}
	
	public void walkOut() {
		if(super.getPositionX() != 178) {
			super.setVelocity(1, 0);
			super.setFacing("RIGHT");
		}
		else if(super.getPositionY() != 458) {
			super.setVelocity(0, 1);
			super.setFacing("DOWN");
		}
		else {
			super.setActive(false);
		}
	}
	
	@Override
	public void update() {
		walk();
		super.update();
	}
	

}
