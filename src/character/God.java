package character;

import UI.Images;
import controller.GameManager;
import map.Map;

public class God extends Visitor{

	public God(Map map) {
		super(Images.GODL, Images.GODR, Images.GODU, Images.GODD, map);
	}
	
	@Override
	public void talkWithContactPerson() {
		if(talkTick == 0) {
			Thread t = new Thread(()->{
				try {
					this.showMessage(Images.X10, 1000);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			t.start();
		}
		this.talkTick++;
		if(talkTick == 100) {
			this.stage = 3;
			GameManager.setCustomer(GameManager.getCustomer() + 1);
			GameManager.setAvailableRoom(GameManager.getAvailableRoom() - 1);
			GameManager.addPopularity();
			GameManager.getPlayer().addMoney(this.room.getFee()*10);
		}	
	}

}
