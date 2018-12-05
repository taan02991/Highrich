package character;

import UI.Images;
import controller.GameManager;
import controller.KeyInput;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import map.Map;

public class God extends Visitor{

	public God(Map map) {
		super(Images.GODL, Images.GODR, Images.GODU, Images.GODD, map);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("New Event");
		alert.setHeaderText("God visit your hotel");
		alert.setContentText("You will receive massive of money from the god");
		alert.show();
		KeyInput.removeKey("UP");
		KeyInput.removeKey("DOWN");
		KeyInput.removeKey("LEFT");
		KeyInput.removeKey("RIGHT");
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
			this.state = 3;
			GameManager.setCustomer(GameManager.getCustomer() + 1);
			GameManager.setAvailableRoom(GameManager.getAvailableRoom() - 1);
			GameManager.addPopularity();
			GameManager.getPlayer().addMoney(this.room.getFee()*10);
		}	
	}

}
