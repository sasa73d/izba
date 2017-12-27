package org.izba.first_rest.databas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.izba.first_rest.exception.DBSqlException;
import org.izba.first_rest.model.Photo;

public class PhotoDB {
	
	private PreparedStatement stmt;
	
	public List<Photo> listPhotos(String sql) {
		List<Photo> photos = new ArrayList<>();
		initialiseDB(sql);
		try {
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				Photo photo = new Photo
						(rset.getLong(1),
						rset.getLong(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5));
				photos.add(photo);
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB errorr: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		return photos;
	}
	
	public Photo insertUpdatePhoto(Photo photo, String sql, boolean isInsert) {
		initialiseDB(sql);
		try {
			stmt.setLong(1, photo.getAlbumId());
			stmt.setString(2, photo.getTitle());
			stmt.setString(3, photo.getUrl());
			stmt.setString(4, photo.getThumbnailUrl());
			stmt.execute();
			if (isInsert) {
				photo.setId(getInsertedId());
			}
		} catch (SQLException ex) {
			throw new DBSqlException("DB error: " + ex.getMessage() + " error code: " + ex.getErrorCode());
		}
		
		return photo;
	}
	
	public void deletePhoto(String sql, long id) {
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
		String sql = "select id from photos order by id desc limit 1";
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
