package org.izba.first_rest.service;

import java.util.List;

import org.izba.first_rest.databas.UserDB;
import org.izba.first_rest.enums.UserSql;
import org.izba.first_rest.exception.DataNotFoundException;
import org.izba.first_rest.model.User;

public class UserService {
	
	private UserDB userDB = new UserDB();
	
	public List<User> getAllUsers() {
		String sql = UserSql.SQL.toString();
		List<User> user = userDB.listUsers(sql);
		if (user.size() <= 0){
			throw new DataNotFoundException("Not found records");
		}
		return user;
	}
	
	public List<User> getUserById(Long id) {
		String sql = UserSql.SQL_WHERE.toString() + id;
		List<User> user = userDB.listUsers(sql);
		if (user.size() <= 0){
			throw new DataNotFoundException("Not found record with id: " + id);
		}
		return user;
	}
	
	public User addUser(User user) {
		String sql = UserSql.SQL_INSERT.toString();
		return userDB.insertUpdateUser(user, sql, true);
	}
	
	public User updateUser(User user) {
		if (user.getId() <= 0) {
			return null;
		}
		String sql = UserSql.SQL_UPDATE.toString() + user.getId(); 
		return userDB.insertUpdateUser(user, sql, false);	
	}
	
	public void removeUser(Long id) {
		String sql = UserSql.SQL_DELETE.toString();
		userDB.deleteUser(sql, id);
	}

}
