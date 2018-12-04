package exception;

public class WarpToTerraceException extends Exception{

	private static final long serialVersionUID = -2191423090320604677L;

	public WarpToTerraceException() {
		System.out.println("Can't warp to Terrace");
	}
}
