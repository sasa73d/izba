package org.izba.first_rest.service;

import java.util.List;

import org.izba.first_rest.databas.PhotoDB;
import org.izba.first_rest.enums.PhotoSql;
import org.izba.first_rest.exception.DataNotFoundException;
import org.izba.first_rest.model.Photo;

public class PhotoService {
	
	private PhotoDB photoDB = new PhotoDB();
	
	public List<Photo> getAllPhotos() {
		String sql = PhotoSql.SQL.toString();
		List<Photo> photos = photoDB.listPhotos(sql);
		if (photos.size() <= 0){
			throw new DataNotFoundException("Not found records");
		}
		return photos;
	}
	
	public List<Photo> getPhotoById(Long id, long albumId) {
		String sql = PhotoSql.SQL.toString();
		if (id > 0 && albumId <= 0) {
			sql = sql + " where id = " + id;
		} else if (id <= 0 && albumId > 0) {
			sql = sql + " where albumId = " + albumId;
		} else if (id > 0 && albumId > 0) {
			sql = sql + " where id = " + id + " or albumId = " + albumId;
		}
		List<Photo> photos = photoDB.listPhotos(sql);
		if (photos.size() <= 0){
			throw new DataNotFoundException("Not found record with id: " + id);
		}
		return photos;
	}
	
	public Photo addPhoto(Photo photo) {
		String sql = PhotoSql.SQL_INSERT.toString();
		return photoDB.insertUpdatePhoto(photo, sql, true);
	}
	
	public Photo updatePhoto(Photo photo) {
		if (photo.getId() <= 0) {
			return null;
		}
		String sql = PhotoSql.SQL_UPDATE.toString() + photo.getId();
		return photoDB.insertUpdatePhoto(photo, sql, false);	
	}
	
	public void removePhoto(Long id) {
		String sql = PhotoSql.SQL_DELETE.toString();
		photoDB.deletePhoto(sql, id);
	}

}
