package controller;

import java.util.ArrayList;
import UI.Images;
import character.Player;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;

public class GameManager {
	private static final int MAXROOM = 30;
	private static Player player;
	private static ArrayList<Map> maps;
	private static Map currentMap;
	private static boolean gamePausing;
	private static int customer;
	private static int popularity;
	private static int availableRoom;
	private static int nStandard;
	private static int nExecutive;
	private static int nPresidential;
	private static long gameTick;
	private static int day;
	
	public GameManager() {
		maps = new ArrayList<Map>();
		generateMap();
		currentMap = maps.get(0);
		gamePausing = false;
		gameTick = 0;
		customer = 0;
		popularity = 50;
		day = 0;
		availableRoom = 0;
		nStandard = 0;
		nExecutive = 0;
		nPresidential = 0;
		player = new Player(Images.PLAYERR, currentMap, 0, 0, 0, 0);
	}
	
	public static void generateMap() {
		maps.add(new MapWelcome());
		for(int i = 0; i < 5; i++) {
			maps.add(new MapUpStair(i));
		}
	}
	
	public void update(GraphicsContext gc){
		
		//test add Receptionist
		gameTick = (gameTick + 1) % 11250;
		//for test
		if(KeyInput.contains("X")) {
			((MapWelcome)maps.get(0)).addVisitor();
		}
		
		if(Time.getHour() < 6 || Time.getHour() > 12) {
			if(gameTick % (400 - ((MapWelcome)maps.get(0)).getNumberOfReceptionist()*20 + customer*10) == 0) {
				((MapWelcome)maps.get(0)).addVisitor();
				System.out.println(gameTick + "");
			}
		}
			
		currentMap = maps.get((maps.indexOf(currentMap) + player.warp()) % maps.size());
		player.setMap(currentMap);
		for(Map map: maps) {
			map.updateNpc();
		}
		player.update();
			
			
		if(Time.getHour() == 12 && Time.getMin() == 0) {
			for(Map m: maps) {
				if(m instanceof MapUpStair) {
					((MapUpStair) m).clear();
				}
			}
			availableRoom = nStandard + nExecutive + nPresidential;
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
	
	public static boolean isWin() {
		//test
		return true;
//		
//		if(nPresidential == MAXROOM) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}

	public static boolean isGamePausing() {
		return gamePausing;
	}

	public static void setGamePausing(boolean gamePausing) {
		GameManager.gamePausing = gamePausing;
	}
	
	public static void addPopularity() {
		if(popularity < 100) {
			popularity += 1;
		}
	}
	
	public static void minusPopularity() {
		if(popularity > 1) {
			popularity -= 1;
		}
	}

	public static int getPopularity() {
		return popularity;
	}

	public static int getCustomer() {
		return customer;
	}

	public static void setCustomer(int n) {
		customer = n;
	}

	public static int getDay() {
		return day;
	}

	public static void setDay(int day) {
		GameManager.day = day;
	}

	public static int getAvailableRoom() {
		return availableRoom;
	}

	public static void setAvailableRoom(int availableRoom) {
		GameManager.availableRoom = availableRoom;
	}

	public static int getnStandard() {
		return nStandard;
	}

	public static void setnStandard(int nStandard) {
		GameManager.nStandard = nStandard;
	}

	public static int getnExecutive() {
		return nExecutive;
	}

	public static void setnExecutive(int nExecutive) {
		GameManager.nExecutive = nExecutive;
	}

	public static int getnPresidential() {
		return nPresidential;
	}

	public static void setnPresidential(int nPresidential) {
		GameManager.nPresidential = nPresidential;
	}
	
	
}
