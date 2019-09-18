package model;

public class User {
	
	private int userId; // auto generated that uniquely identifies a user;
	private String userName; 
	private String userPwd; // user password 
	private int acctNumber; // the bank account number auto generated
	private double accountBalance;
	
	//auto generate constructor
	public User(int userId, String userName, String userPwd, int acctNumber, double accountBalance) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.acctNumber = acctNumber;
		this.accountBalance = accountBalance;
	}

	public User() {
	}

	//auto generate getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(int acctNumber) {
		this.acctNumber = acctNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	
	//auto generate toString method
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", acctNumber="
				+ acctNumber + ", accountBalance=" + accountBalance + "]";
	} 
}
