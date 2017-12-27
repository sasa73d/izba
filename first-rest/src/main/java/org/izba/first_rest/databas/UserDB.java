package org.izba.first_rest.databas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.izba.first_rest.exception.DBSqlException;
import org.izba.first_rest.model.User;
import org.izba.first_rest.model.under_user.Address;
import org.izba.first_rest.model.under_user.Company;
import org.izba.first_rest.model.under_user.Geo;

public class UserDB {
	
	private PreparedStatement stmt;
	
	public List<User> listUsers(String sql) {
		List<User> users = new ArrayList<>();
		
		initialiseDB(sql);
		try {
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				Geo geo = new Geo
						(rset.getString(9), rset.getString(10));
				Address address = new Address
						(rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), geo);
				Company company = new Company
						(rset.getString(13), rset.getString(14), rset.getString(15));
				User user = new User
						(rset.getLong(1),
						rset.getString(2), 
						rset.getString(3), 
						rset.getString(4), 
						address, 
						rset.getString(11), 
						rset.getString(12), 
						company);
			users.add(user);
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		
		return users;
	}
	
	public User insertUpdateUser(User user, String sql, boolean isInsert) {
		initialiseDB(sql);
		try {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAddress().getStreet());
			stmt.setString(5, user.getAddress().getSuite());
			stmt.setString(6, user.getAddress().getCity());
			stmt.setString(7, user.getAddress().getZipcode());
			stmt.setString(8, user.getAddress().getGeo().getLat());
			stmt.setString(9, user.getAddress().getGeo().getLng());
			stmt.setString(10, user.getPhone());
			stmt.setString(11, user.getWebsite());
			stmt.setString(12, user.getCompany().getName());
			stmt.setString(13, user.getCompany().getCatchPhrase());
			stmt.setString(14, user.getCompany().getBs());
			stmt.execute();
			if (isInsert) {
				user.setId(getInsertedId());
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		
		return user;
	}
	
	public void deleteUser(String sql, long id) {
		try {
			initialiseDB(sql);

			stmt.setLong(1, id);
			stmt.execute();

		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
	}
	
	private void initialiseDB(String sql) {
		String url = "jdbc:mysql://localhost:3306/izba";
		String username= "root";
		String password = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			// Load the JDBC driver
			Class.forName(driver);
			// Connect to a database
			Connection con = DriverManager.getConnection
			(url , username, password);
			stmt = con.prepareStatement(sql);
		} catch (Exception ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage());
		}
		
	}
	
	private long getInsertedId() {
		String sql = "select id from users order by id desc limit 1";
		long index = 0;
		initialiseDB(sql);
		try { 
			ResultSet rset = stmt.executeQuery(sql);
			if (rset.next()) {
				index = rset.getLong(1);
				}
		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		return index;
	}
}