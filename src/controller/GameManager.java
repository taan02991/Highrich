package controller;

import java.util.ArrayList;
import java.util.Optional;

import UI.GameScene;
import UI.Images;
import character.Player;
import character.Visitor;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;
import map.Room;
import map.RoomConstruction;
import map.RoomExecutive;
import map.RoomPresidential;
import map.RoomStandard;

public class GameManager {
	private static Player player;
	private static ArrayList<Map> maps;
	private static Map currentMap;
	private static boolean gamePausing;
	private static boolean isGameOver;
	private static int Customer;
	private static int AvailableRoom;
	private static int TotalRoom;
	private static int floor;
	
	//for test
	private long gameTick = 0;
	
	public GameManager() {
		this.maps = new ArrayList<Map>();
		this.generateMap();
		this.currentMap = maps.get(0);
		this.gamePausing = false;
		player = new Player(Images.PLAYERU, this.currentMap, 0, 0, 0, 0);
	}
	
	public void generateMap() {
		maps.add(new MapWelcome());
		for(int i = 0; i < 6; i++) {
			maps.add(new MapUpStair(i));
		}
	}
	
	public void update() {
		//test add Receptionist
		if(!this.gamePausing) {
			gameTick = (gameTick+1)%100000;
			if(this.currentMap instanceof MapWelcome && gameTick%300 == 0) {
				((MapWelcome) this.currentMap).addReceptionist();	
			}
			if(KeyInput.contains("SPACE")) {
				System.out.println(((MapWelcome) this.currentMap).addVisitor());
			}
			// Change current map if player is on warp position
			this.currentMap = this.maps.get((this.maps.indexOf(this.currentMap) + player.warp()) % this.maps.size());
			//Change map player is at
			player.setMap(this.currentMap);
			for(Map map: maps) {
				map.updateNpc();
			}
			player.update();
			
			/*map up stair
			*check player intersects with tractor
			*/
			if(this.currentMap instanceof MapUpStair) {
				for(Room o : ((MapUpStair) this.currentMap).getRoomsList()) {
					if(player.intersects(o.getTractor()) && KeyInput.contains("ENTER")) {
						player.buyRoom(o);
						System.out.println(Player.getMoney());
						this.gamePausing = true;
					}
				}
				
			}
		}
		else {
			if(KeyInput.contains("ENTER")) {
				this.gamePausing = false;
			}
		}

	}
	
	public void render(GraphicsContext gc) {
		this.currentMap.render(gc);
		player.render(gc);
	}

	public static Player getPlayer() {
		return player;
	}

	public ArrayList<Map> getMaps() {
		return maps;
	}
	
	
	
	
	
	
}
