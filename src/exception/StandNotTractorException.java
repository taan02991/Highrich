package exception;

public class StandNotTractorException extends Exception{
	public StandNotTractorException(String s) {
		System.out.println("Stand Not "+s);
	}
}
