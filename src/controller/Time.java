package controller;

public class Time{
	private static int hour = 0;
	private static int min = 0;
	private static int count = 0;
	
	public Time() {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!GameManager.isGamePausing()) {
						count++;
						convert();											
					}
				}
			}
			
		});
		t.start();
	}
	
	private void convert() {
		if(count >= 1440) {
			count = 0;
			GameManager.setDay(GameManager.getDay()+1);
		}
		hour = (int) count/60;
		min = (int) count%60;
	}

	public static int getHour() {
		return hour;
	}

	public static int getMin() {
		return min;
	}
	
}
