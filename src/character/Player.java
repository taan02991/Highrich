package character;

import UI.AnimatedImage;
import UI.Audio;
import UI.Images;
import controller.GameManager;
import controller.KeyInput;
import exception.MoneyNotEnoughtException;
import exception.ReceptionistFullException;
import exception.RoomIsHighestClassException;
import exception.StandNotTractorException;
import controller.BuyRoom;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;
import map.Room;
import map.RoomConstruction;
import map.RoomExecutive;
import map.RoomPresidential;
import map.RoomStandard;

public class Player extends AnimatedImage implements Walkable{
	private static final Image[] PLAYERL = Images.PLAYERL;
	private static final Image[] PLAYERR = Images.PLAYERR;
	private static final Image[] PLAYERU = Images.PLAYERU;
	private static final Image[] PLAYERD = Images.PLAYERD;
	private static int Money;
	
	public Player(Image[] frames, Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
		Money = 200000;
	}
	
	public void buyReceptionist() {
		MapWelcome mapWelcome = ((MapWelcome)GameManager.getMaps().get(0));
		try {
			this.enoughMoney(Receptionist.getCost());
			mapWelcome.addReceptionist();
			System.out.println("Add Receptionist" + getMoney());
		}catch(MoneyNotEnoughtException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("You can't buy new Receptionist");
			alert.setContentText("You don't have enought money for new Receptionist");
			alert.showAndWait();
		}catch(ReceptionistFullException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("You can't buy new Receptionist");
			alert.setContentText("Receptionists are over limit");
			alert.showAndWait();
		}
	}
	
	private void enoughMoney(int n) throws MoneyNotEnoughtException {
		if(Money < n) {
			throw new MoneyNotEnoughtException();
		}
	}
	
	private void checkRoomPresidential(Room room) throws RoomIsHighestClassException {
		if(room instanceof RoomPresidential) {
			throw new RoomIsHighestClassException();
		}
	}
	
	private void throwNotStandAtTractor() throws StandNotTractorException {
		throw new StandNotTractorException("tractor icon");
	}
	
	private void throwNotStandAtMapUpStair() throws StandNotTractorException{
		throw new StandNotTractorException("map up stair");
	}
	
	public static void addMoney(int m) {
		Money += m;
		System.out.println(Money);
	}

	public static int getMoney() {
		return Money;
	}


	public void payMoney(int o) {
		if( Money - o > 0 ) {
			Money -= o;
		}
	}
	
	public void buyRoom(GraphicsContext gc){		
		try {
			if(this.getMap() instanceof MapUpStair) {
				for(Room room: ((MapUpStair) this.getMap()).getRoomsList()) {
					
					if(this.intersects(room.getTractor())) {
						//check this room is presidential for throw exception
						this.checkRoomPresidential(room);
						if( room instanceof RoomConstruction) {
							//check have enough money for throw exception
							this.enoughMoney(room.getConstructionCost());
							payMoney(room.getConstructionCost());
							new BuyRoom(super.getMap(), room, 1, gc);
							return;
						}else if( room instanceof RoomStandard) {
							//check have enough money for throw exception
							this.enoughMoney(room.getConstructionCost());
							payMoney(room.getConstructionCost());
							new BuyRoom(super.getMap(), room, 2, gc);
							return;
							
						}else if( room instanceof RoomExecutive) {
							//check have enough money for throw exception
							this.enoughMoney(room.getConstructionCost());
							payMoney(room.getConstructionCost());
							new BuyRoom(super.getMap(), room, 3, gc);
							return;
							
						}
						this.throwNotStandAtTractor();
					}
				}
			}
			this.throwNotStandAtMapUpStair();			
		}catch(MoneyNotEnoughtException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("You can't buy the room");
			alert.setContentText("You don't have enought money for buy this room");
			alert.showAndWait();
		}catch(RoomIsHighestClassException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("You can't buy the room");
			alert.setContentText("This room is already highest class");
			alert.showAndWait();
		}catch(StandNotTractorException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("You can't buy the room");
			alert.setContentText("You must stand at Tractor icon");
			alert.showAndWait();
		}
	}

	public void setFacing() {
		if(super.getVelocityX() < 0) {
			super.setFrame(PLAYERL);
		}
		else if(super.getVelocityX() > 0) {
			super.setFrame(PLAYERR);
		}
		else if(super.getVelocityY() > 0) {
			super.setFrame(PLAYERD);
		}
		else if(super.getVelocityY() < 0) {
			super.setFrame(PLAYERU);
		}
	}
	
	public void setVelocityOnKeyPressed() {
		super.setVelocity(0, 0);
    	if(KeyInput.contains("RIGHT")) {
    		super.setVelocity(2, 0);
    	}
    	if(KeyInput.contains("LEFT")) {
    		super.setVelocity(-2, 0);
    	}
    	if(KeyInput.contains("UP")) {
    		super.setVelocity(0, -2);
    	}
    	if(KeyInput.contains("DOWN")) {
    		super.setVelocity(0, 2);
    	}
	}
	
	public int warp() {
		if(super.getMap().getWarpUp() != null && super.getMap().getWarpUp().intersects(this)) {	
			super.setPosition(230, 500 - this.getHeight() - 20);
			Audio.WARP.setVolume(1);
			Audio.WARP.play();
			return 1;
		}
		else if(super.getMap().getWarpDown() != null && super.getMap().getWarpDown().intersects(this)) {
			this.setPosition(230, 20);
			Audio.WARP.setVolume(1);
			Audio.WARP.play();
			return -1;
		}
		return 0;
	}
	
	
	@Override
	public void walk() {
		this.setVelocityOnKeyPressed();
		this.setFacing();	
	}
	
	@Override 
	public void update() {
    	this.walk();
    	super.update();
	}

	
	
	
	
}
