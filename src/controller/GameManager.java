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
	private static int Customer;
	private static long gameTick = 0;
	
	public GameManager() {
		maps = new ArrayList<Map>();
		generateMap();
		currentMap = maps.get(0);
		gamePausing = false;
		Customer = 0;
		player = new Player(Images.PLAYERU, currentMap, 0, 0, 0, 0);
	}
	
	public static void generateMap() {
		maps.add(new MapWelcome());
		for(int i = 0; i < 3; i++) {
			maps.add(new MapUpStair(i));
		}
	}
	
	public void update() {
		//test add Receptionist
		if(!gamePausing) {
			gameTick = (gameTick + 1) % 11250;
			if(gameTick % (500 + Customer*100) == 0
				&& ((MapWelcome) maps.get(0)).addVisitor()) {
//				System.out.println(gameTick);
				Customer = Customer + 1;
//				System.out.println(Customer);
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
						player.buyRoom(o);
						System.out.println(player.getMoney());
						gamePausing = true;
					}
				}
			}
		}
		else {
			if(KeyInput.contains("ENTER")) {
				gamePausing = false;
			}
		}

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
	
	
	
	
	
	
}
