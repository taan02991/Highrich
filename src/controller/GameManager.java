package controller;

import java.util.ArrayList;

import UI.Images;
import character.Player;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;

public class GameManager {
	private Player player;
	private ArrayList<Map> maps;
	private Map currentMap;
	private long gameTick = 0;
	
	public GameManager() {
		this.maps = new ArrayList<Map>();
		this.generateMap();
		this.currentMap = maps.get(0);
		player = new Player(Images.playerU, this.currentMap, 0, 0, 0, 0);
	}
	
	public void generateMap() {
		maps.add(new MapWelcome());
		for(int i = 0; i < 6; i++) {
			maps.add(new MapUpStair());
		}
	}
	
	public void update() {
		//test add Receptionist
		gameTick = (gameTick+1)%100000;
		if(this.currentMap instanceof MapWelcome && gameTick%1000 == 0) {
			((MapWelcome) this.currentMap).addReceptionist(1);
		}
		// test warp
		if(this.currentMap.warpUp != null && this.currentMap.warpUp.intersects(player)) {
			this.currentMap = this.maps.get(this.maps.indexOf(this.currentMap) + 1);
			player.setPosition(100, 100);
		}
		else if(this.currentMap.warpDown != null && 
				this.currentMap.warpDown.intersects(player)) {
			this.currentMap = this.maps.get(this.maps.indexOf(this.currentMap) - 1);
			player.setPosition(100, 100);
		}
		//Change map player is at
		player.setMap(this.currentMap);
		//
		this.currentMap.updateNpc();
		this.player.update();
	}
	
	public void render(GraphicsContext gc) {
		this.currentMap.render(gc);
		this.player.render(gc);
	}
	
	
	
	
}
