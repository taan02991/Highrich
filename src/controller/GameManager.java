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
	private ArrayList<Map> maps;
	private Map currentMap;
	//for test
	private long gameTick = 0;
	private int stage = 0;
	private boolean gamePausing;
	
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
			if(KeyInput.contains("SPACE") && this.stage == 1) {
				this.stage = 0;
				System.out.println(((MapWelcome) this.currentMap).addVisitor());
			}
			else if(!KeyInput.contains("SPACE")){
				this.stage = 1;
			}
			// Change current map if player is on warp position
			this.currentMap = this.maps.get((this.maps.indexOf(this.currentMap) + player.warp()) % this.maps.size());
			//Change map player is at
			player.setMap(this.currentMap);
			for(Map map: maps) {
				map.updateNpc();
			}
			player.update();
			
			if(this.currentMap instanceof MapUpStair) {
				for(Room r: ((MapUpStair) this.currentMap).getRoomsList()) {
					if(player.intersects(r.getTractor()) && KeyInput.contains("ENTER") && this.stage == 1) {
						System.out.println("Game is played");
						this.gamePausing = true;
						this.stage = 0;
						System.out.println("Show Menu");
					}
					else if(!KeyInput.contains("ENTER")){
						this.stage = 1;
					}
				}
			}
		}
		else {
			if(KeyInput.contains("ENTER") && this.stage == 1){
				System.out.println("Game is paused");
	 			this.gamePausing = false;
				this.stage = 0;
			}
			else if(!KeyInput.contains("ENTER")){
				this.stage = 1;
			}
		}
			
		
		/*map up stair
		*check player intersects with tractor
		*/
		
		/*if(this.currentMap instanceof MapUpStair) {
			for(Room o : ((MapUpStair) this.currentMap).getRoomsList()) {
				
				if(player.intersects(o.getTractor()) && KeyInput.contains("ENTER") && this.stage == 0) {
					
					//if room is construction , the first pay change to Standard
					if( o instanceof RoomConstruction) {
						player.payMoney(5000);
						((MapUpStair) this.currentMap).setRoom(o.getPosition(), 1);
						System.out.println("change to Standard");
					}else if( o instanceof RoomStandard) {
						player.payMoney(10000);
						((MapUpStair) this.currentMap).setRoom(o.getPosition(), 2);
						System.out.println("change to Excecutive");
					}else if( o instanceof RoomExecutive) {
						player.payMoney(20000);
						((MapUpStair) this.currentMap).setRoom(o.getPosition(), 3);
						System.out.println("change to Presidential");
					}else if( o instanceof RoomPresidential) {
						player.payMoney(40000);
						((MapUpStair) this.currentMap).setRoom(o.getPosition(), 4);
						System.out.println("aleary Presidential");
					}
					
					//this.gamePausing = true;
					this.stage = 1;
					
					
				}
				else if(player.intersects(o.getTractor()) && !KeyInput.contains("ENTER")){
					this.stage = 0;
				}
					
					this.isBumpTractor[1] = true;
					System.out.println("1 " + this.isBumpTractor[0]+" "+this.isBumpTractor[1]);
					
					ArrayList<String> choices = new ArrayList<>();
					choices.add("a");
					choices.add("b");
					choices.add("c");

					ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
					dialog.setTitle("Choice Dialog");
					dialog.setHeaderText("Look, a Choice Dialog");
					dialog.setContentText("Choose your letter:");

					// Traditional way to get the response value.
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
					    System.out.println("Your choice: " + result.get());
					}

					// The Java 8 way to get the response value (with lambda expression).
					result.ifPresent(letter -> System.out.println("Your choice: " + letter));
					((MapUpStair) this.currentMap).setRoom(o.getPosition());
				}
				else {
					this.isBumpTractor[1] = false;
				}
				
			}
		}
		}
		else{
			if(KeyInput.contains("ENTER") && this.stage == 0) {
				System.out.println("play");
				this.gamePausing = false;
				this.stage = 1;
			}
			else if(!KeyInput.contains("ENTER")){
				this.stage = 0;
			}
		}*/
				
	}
	
	public void render(GraphicsContext gc) {
		this.currentMap.render(gc);
		player.render(gc);
	}
	
	
	
	
	
	
}
