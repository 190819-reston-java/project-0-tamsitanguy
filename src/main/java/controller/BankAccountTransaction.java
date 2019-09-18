package controller;

import java.util.Scanner;

public class BankAccountTransaction {
	
	 
	private int pinNumber;//pin number used to make any transactions
	private int accNumber; //The account number unique for each bank account
	private static double acctBalance;// showing the balance
	Scanner takeInput = new Scanner(System.in);
	
	// Auto generate getters and setters for each field
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public double getAcctBanlance() {
		return acctBalance;
	}
	public void setAcctBanlance(double acctBanlance) {
		this.acctBalance = acctBanlance;
	}
	
	public static void checkBalance() {
		
		double balance= acctBalance;
		System.out.println("your current balance is:$ "+balance);
	}
	
	// The makeDeposit method takes in the deposit amount entered by the user.
	// It only accepts deposits greater than zero.
	public void makeDeposit(double depositAmount) {
		//for testing
		System.out.println("Enter the deposit amount: ");
		depositAmount = takeInput.nextDouble();
		
		if(depositAmount <= 0.00) {
			System.out.println("Sorry, you cannot deposit an amount less than 0");
		}
		this.acctBalance= acctBalance + depositAmount;
	}
	
	// The withdrawMoney method helps withdraw some money from the account
	// it checks if the user has enough money before withdrawing
	
	public void withdrawMoney(double amountWithdraw) {
		double newCurrentBalance;
		System.out.println("Please enter the withdraw amount: ");
		amountWithdraw = takeInput.nextDouble();
		if (amountWithdraw > acctBalance) {
			System.out.println("Sorry, insufficient balance, withdraw amount cannot"
					+ " be greater than your current balance");
		}
		newCurrentBalance = acctBalance - amountWithdraw;
		acctBalance = newCurrentBalance;
		
	}
	
	
}
