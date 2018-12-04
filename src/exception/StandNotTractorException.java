package exception;

public class StandNotTractorException extends Exception{

	private static final long serialVersionUID = 4425178354312483007L;

	public StandNotTractorException(String s) {
		System.out.println("Stand Not "+s);
	}
}
