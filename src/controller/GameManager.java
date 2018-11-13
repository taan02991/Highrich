package controller;

import java.util.ArrayList;
import java.util.Optional;

import UI.GameScene;
import UI.Images;
import character.Player;
import character.Visitor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import map.Map;
import map.MapUpStair;
import map.MapWelcome;
import map.Room;

public class GameManager {
	private static Player player;
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
			maps.add(new MapUpStair(i));
		}
	}
	
	public void update() {
		//test add Receptionist
		gameTick = (gameTick+1)%100000;
		if(this.currentMap instanceof MapWelcome && gameTick%300 == 0) {
			((MapWelcome) this.currentMap).addReceptionist(1);
			((MapWelcome) this.currentMap).addNpc(new Visitor(Images.PLAYERL, Images.PLAYERR, Images.PLAYERU, Images.PLAYERD, this.currentMap));
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
				if( player.intersects(o.getTractor()) ) {
					System.out.println("xxx");
					/*ArrayList<String> choices = new ArrayList<>();
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
					((MapUpStair) this.currentMap).setRoom(o.getPosition());*/

				}
			}
		}
	}
	
	public void render(GraphicsContext gc) {
		this.currentMap.render(gc);
		player.render(gc);
	}
	
	
	
	
	
	
}
