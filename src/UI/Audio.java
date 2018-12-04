package UI;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final AudioClip BGM = new AudioClip(ClassLoader.getSystemResource("BGM.wav").toString());
	public static final AudioClip BGMSTARTSCENE = new AudioClip(ClassLoader.getSystemResource("BGMStartScene.wav").toString());
	public static final AudioClip WARP = new AudioClip(ClassLoader.getSystemResource("Warp.wav").toString());
	public static final AudioClip MENU = new AudioClip(ClassLoader.getSystemResource("Menu.wav").toString());
}
