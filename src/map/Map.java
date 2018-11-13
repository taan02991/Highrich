package map;

import java.util.ArrayList;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Map{
	private Rectangle background;
	private ArrayList<Rectangle> structList;
	private ArrayList<Npc> npcList;
	private Rectangle warpUp, warpDown;
	
	public Map(){
		this.warpUp = null;
		this.warpDown = null;
		this.structList = new ArrayList<Rectangle>();
		this.npcList = new ArrayList<Npc>();
	}
	
	public abstract void render(GraphicsContext gc);
	
	// test updateNpc
	public void updateNpc() {
		ArrayList<Npc> inActive = new ArrayList<Npc>();
		for(Npc npc: this.getNpcList()) {
			npc.update();
			if(!npc.isActive()) {
				inActive.add(npc);
			}
		}
		this.getNpcList().removeAll(inActive);
		
	}
	
	
	public void addStruct(Rectangle rectangle) {
		this.structList.add(rectangle);
	}
	
	public void addNpc(Npc npc) {
		this.npcList.add(npc);
	}
	
	public Rectangle getBackground() {
		return this.background;
	}
	
	public void setBackground(Image i) {
		this.background = new Rectangle(i, 0, 0);
	}
	

	public ArrayList<Rectangle> getStructList() {
		return structList;
	}

	public ArrayList<Npc> getNpcList() {
		return npcList;
	}
	
	public void setWarpUp(Rectangle warpUp) {
		this.warpUp = warpUp;
	}
	
	public Rectangle getWarpUp() {
		return warpUp;
	}

	public void setWarpDown(Rectangle warpDown) {
		this.warpDown = warpDown;
	}

	public Rectangle getWarpDown() {
		return warpDown;
	}
	
	
}
