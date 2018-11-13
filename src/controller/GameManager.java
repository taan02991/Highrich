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
		player = new Player(Images.PLAYERU, this.currentMap, 0, 0, 0, 0);
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
		if(this.currentMap instanceof MapWelcome && gameTick%300 == 0) {
			((MapWelcome) this.currentMap).addReceptionist(1);
		}
		// Change current map if player is on warp position
		this.currentMap = this.maps.get((this.maps.indexOf(this.currentMap) + player.warp()) % this.maps.size());
		//Change map player is at
		this.player.setMap(this.currentMap);
		for(Map map: maps) {
			map.updateNpc();
		}
		this.player.update();
	}
	
	public void render(GraphicsContext gc) {
		this.currentMap.render(gc);
		this.player.render(gc);
	}
	
	
	
	
	
	
}
