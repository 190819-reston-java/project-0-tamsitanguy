package bankacountAppTest;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.UserCLI;
import model.User;

public class WithdrawTesting {
	
	private static User fakebal;
	
	@BeforeClass
	public static void doTest() {
		fakebal = new User(001, "hihi", "hello", 007, 50.50);	
			
		}
	
	@Test
	public void notNegativeDeposit() {
		UserCLI.makeWithdraw(fakebal);
		
		
	}
				

}
