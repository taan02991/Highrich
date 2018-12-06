package UI;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final AudioClip BGM = new AudioClip(ClassLoader.getSystemResource("audio/BGM.wav").toString());
	public static final AudioClip BGMSTARTSCENE = new AudioClip(ClassLoader.getSystemResource("audio/BGMStartScene.wav").toString());
	public static final AudioClip WARP = new AudioClip(ClassLoader.getSystemResource("audio/Warp.wav").toString());
	public static final AudioClip MENU = new AudioClip(ClassLoader.getSystemResource("audio/Menu.wav").toString());
	public static final AudioClip TAP = new AudioClip(ClassLoader.getSystemResource("audio/Tap.wav").toString());
	public static final AudioClip BOMB = new AudioClip(ClassLoader.getSystemResource("audio/Bomb.wav").toString());
}
