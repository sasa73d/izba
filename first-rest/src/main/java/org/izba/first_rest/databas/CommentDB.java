package org.izba.first_rest.databas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.izba.first_rest.exception.DBSqlException;
import org.izba.first_rest.model.Comment;

public class CommentDB {
	
	private PreparedStatement stmt;
	
	public List<Comment> listComments(String sql) {
		List<Comment> comments = new ArrayList<>();
		
		initialiseDB(sql);
		try {
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				Comment comment = new Comment
						(rset.getLong(1),
						rset.getLong(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5));
				comments.add(comment);
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		return comments;
	}
	
	public Comment insertUpdateComment(Comment comment, String sql, boolean isInsert) {
		initialiseDB(sql);
		try {
			stmt.setLong(1, comment.getPostId());
			stmt.setString(2, comment.getName());
			stmt.setString(3, comment.getEmail());
			stmt.setString(4, comment.getBody());
			stmt.execute();
			if (isInsert) {
				comment.setId(getInsertedId());
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB error: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		
		return comment;
	}
	
	public void deleteComment(String sql, long id) {
		try {
			initialiseDB(sql);

			stmt.setLong(1, id);
			stmt.execute();

		} catch (SQLException ex) {
			throw new DBSqlException("DB error: " + ex.getMessage() + " error code: " + ex.getErrorCode());
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
		String sql = "select id from comments order by id desc limit 1";
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
