package controller;

public class Time{
	private static int hour;
	private static int min;
	private static int count;
	
	public Time() {
		hour = 0;
		min = 0;
		count = 0;
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
					convert();					
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
