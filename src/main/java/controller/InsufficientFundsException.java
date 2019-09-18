package controller;

public class InsufficientFundsException extends RuntimeException {

	//private double amount;
	
	public InsufficientFundsException() {
		//System.out.println("Insufficient funds!");
		//this.amount = amount;
		this("Can't withdraw, insufficient amount!");
	}
	
	public InsufficientFundsException(String msg) {
		super(msg);
	}

//	public double getAccountBalance() {
//		return amount;
//	}
}
