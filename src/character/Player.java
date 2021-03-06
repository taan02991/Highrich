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
import exception.WarpToTerraceException;
import controller.BuyRoom;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import map.Map;
import map.MapTerrace;
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
	private int Money;
	
	public Player(Image[] frames, Map map, double positionX, double positionY, double velocityX, double velocityY) {
		super(frames, map, positionX, positionY, velocityX, velocityY);
		Money = 20000;
	}
	
	public void buyReceptionist() {
		MapWelcome mapWelcome = ((MapWelcome)GameManager.getMaps().get(0));
		try {
			this.enoughMoney(Receptionist.getCost());
			this.setMoney(this.getMoney() - Receptionist.getCost());
			mapWelcome.addReceptionist();
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
	
	public void addMoney(int m) {
		this.Money += m;
	}
	
	public void setMoney(int money) {
		this.Money = money;
	}

	public int getMoney() {
		return this.Money;
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
							this.setMoney(this.getMoney() - room.getConstructionCost());
							new BuyRoom(super.getMap(), room, 1, gc);
							return;
						}else if( room instanceof RoomStandard) {
							//check have enough money for throw exception
							this.enoughMoney(room.getConstructionCost());
							this.setMoney(this.getMoney() - room.getConstructionCost());
							new BuyRoom(super.getMap(), room, 2, gc);
							return;
							
						}else if( room instanceof RoomExecutive) {
							//check have enough money for throw exception
							this.enoughMoney(room.getConstructionCost());
							this.setMoney(this.getMoney() - room.getConstructionCost());
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
	
	private void checkWrapToTerrace() throws WarpToTerraceException {
		if(GameManager.getMaps().indexOf(this.getMap()) + 1 >= GameManager.getMaps().size()) {
			if(super.getMap().getWarpUp() != null && super.getMap().getWarpUp().intersects(this)) {
				throw new WarpToTerraceException();
			}
		}
	}
	
	public void warp() {
		try{
			this.checkWrapToTerrace();
		}catch(WarpToTerraceException e) {
			System.out.println("Terrace map is locked until you completed game");
			this.setPosition(this.getPositionX(), 30);
			KeyInput.removeKey("UP");
			KeyInput.removeKey("DOWN");
			KeyInput.removeKey("LEFT");
			KeyInput.removeKey("RIGHT");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Can't Warp up");
			alert.setHeaderText("You can't wrap up");
			alert.setContentText("You can wrap up to terrace when you have maximum presidential room.");
			alert.show();
			return;
		}

		if(super.getMap().getWarpUp() != null && super.getMap().getWarpUp().intersects(this)) {
			GameManager.setCurrentMap(GameManager.getMaps().get(GameManager.getMaps().indexOf(this.getMap()) + 1));
			this.setMap(GameManager.getCurrentMap());
			if(GameManager.getCurrentMap() instanceof MapTerrace) {
				this.setPosition(39, 292);
			}
			else {
				this.setPosition(230, 500 - this.getHeight() - 20);								
			}
			Audio.WARP.setVolume(1);
			Audio.WARP.play();
		}
		else if(super.getMap().getWarpDown() != null && super.getMap().getWarpDown().intersects(this)) {
			GameManager.setCurrentMap(GameManager.getMaps().get(GameManager.getMaps().indexOf(this.getMap()) - 1));
			this.setMap(GameManager.getCurrentMap());
			this.setPosition(230, 20);
			Audio.WARP.setVolume(1);
			Audio.WARP.play();	
		}
	}
	
	public void endWalking() {
		if(this.getPositionX() != 392) {
			this.setVelocity(1, 0);
		}
		else if(this.getPositionY() != 272){
			this.setVelocity(0, -1);
		}
		else {
			this.setVelocity(0, 0);
			this.setFrame(Images.TRANSPARENT);
			MapTerrace.activeAirplane();
		}
	}
	
	@Override
	public void walk() {
		if(this.getMap() instanceof MapTerrace) {
			this.endWalking();
		}
		else {
			this.setVelocityOnKeyPressed();						
		}
		this.setFacing();
	}
	
	@Override 
	public void update() {
    	this.walk();
    	this.warp();
    	super.update();
	}
	
	
}
