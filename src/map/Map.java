package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Map {
	protected Rectangle background;
	private ArrayList<Rectangle> structList;
	protected ArrayList<Npc> npcList;
	public Rectangle warpUp, warpDown;

	public Map(){
		this.warpUp = null;
		this.warpDown = null;
		this.structList = new ArrayList<Rectangle>();
		this.npcList = new ArrayList<Npc>();
	}
	
	
	public void render(GraphicsContext gc) {
		background.render(gc);
		if(this.warpUp != null) warpUp.render(gc);
		if(this.warpDown != null) warpDown.render(gc);
		for(Rectangle r: structList) r.render(gc);
		for(Npc npc: npcList) npc.render(gc);
	}
	
	// test updateNpc
	public void updateNpc() {
		for(Npc npc: npcList) {
			npc.update();
		}
	}
	
	public void setBackground(Image i) {
		this.background = new Rectangle(i, 0, 0);
	}
	
	public void addStruct(Rectangle rectangle) {
		this.structList.add(rectangle);
	}
	
	public void addNpc(Npc npc) {
		this.npcList.add(npc);
	}

	public ArrayList<Rectangle> getStructList() {
		return structList;
	}

	public ArrayList<Npc> getNpcList() {
		return npcList;
	}
	
	
}
