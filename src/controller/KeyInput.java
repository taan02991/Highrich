package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javafx.scene.Scene;

public class KeyInput {
	
	private static HashSet<String> activeKey = new HashSet<String>();
	private static HashSet<String> keyCode = new HashSet<String>();
	private static List<String> directionKey = Arrays.asList(new String[]{"UP", "DOWN", "LEFT", "RIGHT"});
	
	public static void addKey(String kb) {
		if(!directionKey.contains(kb) && !keyCode.contains(kb)) {
			activeKey.add(kb);
		}
		if(directionKey.contains(kb)) {
			activeKey.add(kb);
		}
		keyCode.add(kb);
	}
	
	public static void removeKey(String kb) {
		activeKey.remove(kb);
		keyCode.remove(kb);
	}
	
	public static boolean contains(String kb) {
		if(!directionKey.contains(kb)) {
			return activeKey.remove(kb);
		}
		return activeKey.contains(kb);
	}
	
	public static void setKeyHandler(Scene scene) {
		scene.setOnKeyPressed(e -> {
			addKey(e.getCode().toString());
		});
		scene.setOnKeyReleased(e -> {
			removeKey(e.getCode().toString());
		});
	}
	
	
}
