package character;

import UI.AnimatedImage;
import UI.Images;
import UI.Rectangle;
import controller.GameManager;
import controller.KeyInput;
import javafx.scene.image.Image;
import map.Map;
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
		this.Money = 20000;
	}
	
	public boolean buyReceptionist() {
		if(!(super.getMap() instanceof MapWelcome)) {
			return false;
		}
		MapWelcome mapWelcome = ((MapWelcome)GameManager.getMaps().get(0));
		if(this.enoughMoney(Receptionist.getCost()) && mapWelcome.addReceptionist()) {
			this.payMoney(Receptionist.getCost());
			System.out.println("Add Receptionist" + this.getMoney());
			return true;
		}
		else {
			System.out.println("Can't add Receptionist");
			return false;			
		}
	}
	
	public boolean enoughMoney(int n) {
		if(this.Money >= n) {
			return true;
		}
		else {
			return false;
		}
	}

	public static int getMoney() {
		return Money;
	}


	public boolean payMoney(int o) {
		if( this.Money - o < 0 ) {
			return false;
//throw exception
		}
		else{
			this.Money = this.Money - o;
			return true;
		}
	}
	
	public void buyRoom(Room room) {
		if( room instanceof RoomConstruction) {
			payMoney(5000);
			super.getMap().setRoom(room.getPosition(), 1);
			System.out.println("change to Standard");
			
		}else if( room instanceof RoomStandard) {
			payMoney(10000);
			super.getMap().setRoom(room.getPosition(), 2);
			System.out.println("change to Executive");
			
		}else if( room instanceof RoomExecutive) {
			payMoney(20000);
			super.getMap().setRoom(room.getPosition(), 3);
			System.out.println("change to Presidential");
			
		}else if( room instanceof RoomPresidential) {
			System.out.println("aleary Presidential");
		}
		
		/*switch(level) {
		case 1:payMoney(5000);super.getMap().setRoom(position, 1);break;
		case 2:payMoney(10000);super.getMap().setRoom(position, 2);break;
		case 3:payMoney(20000);super.getMap().setRoom(position, 3);break;
		}*/
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
		if(super.getMap().getWarpUp() != null &&
				super.getMap().getWarpUp().intersects(this)) {	
			super.setPosition(230, 500 - this.getHeight() - 20);
			return 1;
		}
		else if(super.getMap().getWarpDown() != null && 
				super.getMap().getWarpDown().intersects(this)) {
			this.setPosition(230, 20);
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
    	if(KeyInput.contains("Z")) {
    		this.buyReceptionist();    		
    	}
    	super.update();                		                		
	}

	
	
	
	
}
