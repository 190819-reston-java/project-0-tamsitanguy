package repositories;

import java.util.List;
import model.User;

public interface UserDAO {
	
	User getUser(int user_Id);

	User getUserBalance(double balance);
	User getUserNameAndPassword(String userName, String passWord);
	
	boolean updateBalance(User u);
	boolean createUser(User u);
	
	//boolean createUser(User u);   
}
