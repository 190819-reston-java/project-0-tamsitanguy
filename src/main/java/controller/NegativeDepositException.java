package controller;

public class NegativeDepositException extends Exception {
	
	public NegativeDepositException() {
		this("Cannot deposit negative amount");
	}

	public NegativeDepositException(String string) {
		super(string);
	}

}
