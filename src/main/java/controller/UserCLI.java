package controller;

import java.util.Scanner;
import org.apache.log4j.Logger;

import model.User;
import repositories.UserDAO;
import repositories.UserDAOImplUJDBC;
import services.UserService;

public class UserCLI {
	
	//creating a UserService object
	private static UserService userService = new UserService();
	private static Logger logger =Logger.getLogger(UserCLI.class);
	private static int wrongOptionCounter =0;
	public static void menu() {
		
		//logger.info("Testing log4j for my menu");
		
		System.out.println("+========================================================+");
		System.out.println("|                                                        |");
		System.out.println("|             WELCOME TO YOUR                            |");
		System.out.println("|                 LOCAL BANK                             |");
		System.out.println("|                                                        |");
		System.out.println("+========================================================+");
		System.out.println("Please read and select an option to begin\t");
		System.out.println(" 1 ---------- To login");
		System.out.println(" 2 ---------- Create an user");
		System.out.println(" 0 ---------- Zero to quit");
		System.out.println("\n\n\n");
		logger.info("Testing log4j for my menu\n");
		Scanner keyboard = new Scanner(System.in);
		String myChoice = keyboard.nextLine();
		
		switch(myChoice) {
		case "1":
			login(null);
			break;
		case "2":
			break;
		case "0":
			System.out.println("Thank you for your bussiness");
			break;
		default:
			System.out.println("Please type: 1 or 0");
			menu();
			break;	
		}
		
	}
	static UserDAO myUser = new UserDAOImplUJDBC();
	//login method
	private static void login(User loginValidation) {
		Scanner input = new Scanner(System.in);
		//loginValidation = new User();
		//UserDAO myUser = new UserDAOImplUJDBC();
		String userName;
		String passWord;
		System.out.println();
		System.out.println("Please enter your username: ");
		userName = input.nextLine();
		System.out.println("Please enter you password: ");
		passWord = input.nextLine();
		loginValidation = myUser.getUserNameAndPassword(userName, passWord);
		//if(userName.equals("tanguy") && passWord.equals("123")) {
		
		if(loginValidation != null) {
			
			System.out.println("Login Successful! ");
			System.out.println("Welcome :"+loginValidation.getUserName()+"\n");
			subMenu(loginValidation);
		}
		else {
			System.out.println("Wrong credentials, Please try again. "+"\n\n");
			login(loginValidation);
		}
		//return loginValidation;
	 }

	//subMenu after successful login.

	public static void subMenu(User userValidation) {
		
		Scanner input = new Scanner(System.in);
		String myString;
		//double amount=0.0;
		User user =userValidation;
		
		System.out.println("Make your selection: ");
		System.out.println("C------------------- Check balance");
		System.out.println("D------------------- Deposit money");
		System.out.println("W------------------- Withdraw money");
		System.out.println("Q------------------- Quit the program");
		System.out.println("Please choose a letter:------- ");
		myString = input.nextLine();
		
		UserDAOImplUJDBC userApp = new UserDAOImplUJDBC();
	
		switch(myString) {
		case "D":
		case "d":
			try {
				makeUserDeposit(user);
			} catch (NegativeDepositException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(user.getAccountBalance());//for testing
			subMenu(user);
			break;
		case "W":
		case "w":
			try{makeWithdraw(user);}catch (InsufficientFundsException e) {
				System.out.println(e);
			}
			System.out.println(user.getAccountBalance());
			subMenu(user);
			break;
		case "C":
		case "c"://call  getAcctBalance() witch enable the user to check his current balance.
			System.out.println(user.getAccountBalance());
			subMenu(user);
			//System.out.println("Check balance...");
			break;
		case "Q":
		case "q":
			System.out.println("Bye!");
			System.exit(0);
			break;
			default:
				//subMenu(user);
				System.out.println("Letter not listed, Sorry.");
				wrongOptionCounter++;
				logger.debug(myString+ " was not listed ");
				logger.debug("This has been "+ wrongOptionCounter+ " tries");
				
				if(wrongOptionCounter > 5) {
					logger.fatal("Failed 5 times, exiting");
					//System.out.println("Get out");
					
					System.exit(1);
					
				}
				subMenu(user);
				break;
				
		}
	}
	
	//makeUserDeposit() this method will allow the user to make a deposit
	public static void makeUserDeposit(User user) throws NegativeDepositException {
		Scanner input = new Scanner(System.in);
		double amount=0;
		System.out.println("Please enter the deposit amount: $");
		amount = input.nextDouble();
		
		if(amount>= 0) {
			double newAmount = user.getAccountBalance() + amount;
			System.out.println(newAmount);
			user.setAccountBalance(newAmount);
			myUser.updateBalance(user);
		}else {
			System.out.println("Please try again");
			makeUserDeposit(user);
		}
		
	}
	
	//makeWithdraw() allows an user or customer to withdraw some money
	public static void makeWithdraw(User user) {
		Scanner input = new Scanner(System.in);
		double amount=0;
		System.out.println("Please enter the withdraw amount: $");
		System.out.println(user.getAccountBalance());
		amount = input.nextDouble();
		
		try {
		if(amount < user.getAccountBalance()) {
			
		double newAmount = user.getAccountBalance() - amount;
		System.out.println(newAmount);
		user.setAccountBalance(newAmount);
		myUser.updateBalance(user);		
		}else {
			System.out.println("try again...");
			makeWithdraw(user);
		}
		}catch (InsufficientFundsException e) {
			System.out.println("Check your balance...");
		}
	}
	
	
}
