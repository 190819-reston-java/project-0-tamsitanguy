package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.ConnectionUtil;
import utils.StreamCloser;


public class UserDAOImplUJDBC implements UserDAO {
	
	
	@Override
	public User getUser(int user_id) {
		
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM user_account WHERE user_id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, user_id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
	@Override
	public User getUserBalance(double userId) {
		
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM user_account WHERE userId = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setDouble(1, userId);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	/**
	 * Returns an user object created using a single valid ResultSet row
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private User createUserFromRS(ResultSet resultSet) throws SQLException {
		return new User(
				resultSet.getInt("user_id"),
				resultSet.getString("user_name"),
				resultSet.getString("user_password"),
				resultSet.getInt("acct_number"),
				resultSet.getDouble("acct_balance"));
	}

// checking user_name and password for validation
	@Override
	public User getUserNameAndPassword(String user_name, String pass_word) {
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM user_account WHERE user_name = ? and user_password =?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, user_name);
				stmt.setString(2, pass_word);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public boolean updateBalance(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "UPDATE user_account SET user_name=?, user_password=?, acct_number=?," + 
		" acct_balance=? WHERE user_id = ?;";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getUserPwd());
			stmt.setDouble(3, u.getAcctNumber());
			stmt.setDouble(4, u.getAccountBalance());
			stmt.setInt(5, u.getUserId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}
		
	
	
	@Override
	public boolean createUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "INSERT INTO user_account VALUES (DEFAULT, ?, ?, ?, ?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getUserPwd());
			stmt.setDouble(3, u.getAcctNumber());
			stmt.setDouble(4, u.getAccountBalance());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}
	
}


