package controller;

public class Sleeper extends Thread{
	private int timer;
	
	public Sleeper(int timer) {
		this.timer = timer;
		start();
	}
	
	public void run() {
		System.out.println("sleep");
		GameManager.setGamePausing(true);
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GameManager.setGamePausing(false);
		System.out.println("awake");
	}
}
