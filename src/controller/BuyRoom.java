package controller;

import UI.Images;
import UI.ControlBar;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import map.MapUpStair;
import map.Room;

public class BuyRoom extends Thread{
	private Map map;
	private Room room;
	private int level;
	private GraphicsContext gc;
	private int frameNumber;
	private int totalFrame;
	private double x,y;
	private double width;
	
	public BuyRoom(Map map, Room room, int level, GraphicsContext gc) {
		this.map = map;
		this.room = room;
		this.level = level;
		this.gc = gc;
		this.frameNumber = 1;
		this.totalFrame = 36;
		this.width = 0;
		if(room.getPosition() > 2) {
			this.x = 300;
			this.y = 166*(room.getPosition()-3);
		}else {
			this.x = 0;
			this.y = 166*room.getPosition();
		}
		start();
	}
	
	public void run() {
		System.out.println("sleep");
		GameManager.setGamePausing(true);
		ControlBar.getBuyRoom().setDisable(true);
		ControlBar.getbuyRecButton().setDisable(true);
		while(this.frameNumber<this.totalFrame) {
			gc.drawImage(Images.DustConstruction, this.width, 0, 200, 166, this.x, this.y, 200, 166);
			this.frameNumber++;
			width += 200;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		GameManager.getPlayer().setPosition(this.x+84, this.y+67);
		gc.clearRect(this.x, this.y, 200, 166);
		((MapUpStair) this.map).setRoom(this.room.getPosition(), this.level);
		ControlBar.getBuyRoom().setDisable(false);
		ControlBar.getbuyRecButton().setDisable(false);
		GameManager.setGamePausing(false);
		System.out.println("awake");
	}
}
