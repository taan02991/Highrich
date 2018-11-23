package controller;

import java.util.ArrayList;
import UI.Images;
import character.Player;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;
import map.Room;

public class GameManager {
	private static Player player;
	private static ArrayList<Map> maps;
	private static Map currentMap;
	private static boolean gamePausing;
	private static int Customer;
	private static int Popularity;
	private static long gameTick = 0;
	
	public GameManager() {
		maps = new ArrayList<Map>();
		generateMap();
		currentMap = maps.get(0);
		gamePausing = false;
		Customer = 0;
		Popularity = 50;
		player = new Player(Images.PLAYERU, currentMap, 0, 0, 0, 0);
	}
	
	public static void generateMap() {
		maps.add(new MapWelcome());
		for(int i = 0; i < 3; i++) {
			maps.add(new MapUpStair(i));
		}
	}
	
	public void update(GraphicsContext gc){
		//test add Receptionist
		if(!gamePausing) {
			gameTick = (gameTick + 1) % 11250;
			//for test
			if(KeyInput.contains("X")) {
				((MapWelcome)maps.get(0)).addVisitor();
			}
			if(gameTick % (500 + Customer*100) == 0 && ((MapWelcome) maps.get(0)).addVisitor()) {
				Customer = Customer + 1;
			}
			
			currentMap = maps.get((maps.indexOf(currentMap) + player.warp()) % maps.size());
			player.setMap(currentMap);
			for(Map map: maps) {
				map.updateNpc();
			}
			player.update();
			
			/*map up stair
			*check player intersects with tractor
			*/
			if(currentMap instanceof MapUpStair) {
				for(Room o : ((MapUpStair) currentMap).getRoomsList()) {
					if(player.intersects(o.getTractor()) && KeyInput.contains("ENTER")) {
						player.buyRoom(o, gc);
						System.out.println(Player.getMoney());
						//gamePausing = true;
					}
				}
			}
		}
		/*else {
			if(KeyInput.contains("ENTER")) {
				gamePausing = false;
			}
		}*/

	}
	
	public void render(GraphicsContext gc) {
		currentMap.render(gc);
		player.render(gc);
	}

	public static Player getPlayer() {
		return player;
	}

	public static ArrayList<Map> getMaps() {
		return maps;
	}

	public static boolean isGamePausing() {
		return gamePausing;
	}

	public static void setGamePausing(boolean gamePausing) {
		GameManager.gamePausing = gamePausing;
	}
	
	public static void addPopularity() {
		if(Popularity < 100) {
			Popularity += 1;
		}
	}
	
	public static void minusPopularity() {
		if(Popularity > 1) {
			Popularity -= 1;
		}
	}

	public static int getPopularity() {
		return Popularity;
	}
	
	
	
	
}
