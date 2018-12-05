package exception;

public class MoneyNotEnoughtException extends Exception {

	private static final long serialVersionUID = -2525558838547083303L;

	public MoneyNotEnoughtException() {
		System.out.println("not have enough money");
	}
}
