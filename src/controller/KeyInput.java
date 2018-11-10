package controller;

import java.util.HashSet;
import javafx.scene.Scene;

public class KeyInput {
	
	private static HashSet<String> keyCode = new HashSet<String>();
	
	public static void addKey(String kb) {
		keyCode.add(kb);
	}
	
	public static void removeKey(String kb) {
		keyCode.remove(kb);
	}
	
	public static boolean contains(String kb) {
		return keyCode.contains(kb);
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
