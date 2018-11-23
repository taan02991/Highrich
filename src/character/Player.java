package character;

import UI.AnimatedImage;
import UI.Images;
import controller.GameManager;
import controller.KeyInput;
import controller.Sleeper;
import javafx.scene.canvas.GraphicsContext;
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
		Money = 200000;
	}
	
	public boolean buyReceptionist() {
		if(!(super.getMap() instanceof MapWelcome)) {
			return false;
		}
		MapWelcome mapWelcome = ((MapWelcome)GameManager.getMaps().get(0));
		if(this.enoughMoney(Receptionist.getCost()) && mapWelcome.addReceptionist()) {
			this.payMoney(Receptionist.getCost());
			System.out.println("Add Receptionist" + getMoney());
			return true;
		}
		else {
			System.out.println("Can't add Receptionist");
			return false;			
		}
	}
	
	public boolean enoughMoney(int n) {
		//Throw Exception
		if(Money >= n) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void addMoney(int m) {
		Money += m;
		System.out.println(Money);
	}

	public static int getMoney() {
		return Money;
	}


	public boolean payMoney(int o) {
		if( Money - o < 0 ) {
			return false;
//throw exception
		}
		else{
			Money -= o;
			return true;
		}
	}
	
	public void buyRoom(Room room, GraphicsContext gc){
		if( room instanceof RoomConstruction && this.enoughMoney(room.getConstructionCost())) {
			payMoney(room.getConstructionCost());
			room.renderDusty(gc);
			new Sleeper(1000);
			super.getMap().setRoom(room.getPosition(), 1);
			System.out.println("change to Standard");
		}else if( room instanceof RoomStandard && this.enoughMoney(room.getConstructionCost())) {
			payMoney(room.getConstructionCost());
			room.renderDusty(gc);
			new Sleeper(1000);
			super.getMap().setRoom(room.getPosition(), 2);
			System.out.println("change to Executive");
			
		}else if( room instanceof RoomExecutive && this.enoughMoney(room.getConstructionCost())) {
			payMoney(room.getConstructionCost());
			new Sleeper(1000);
			super.getMap().setRoom(room.getPosition(), 3);
			System.out.println("change to Presidential");
			
		}else if( room instanceof RoomPresidential) {
			System.out.println("aleary Presidential");
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
			return 1;
		}
		else if(super.getMap().getWarpDown() != null && super.getMap().getWarpDown().intersects(this)) {
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
