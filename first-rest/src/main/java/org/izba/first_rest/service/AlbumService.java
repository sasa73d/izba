package org.izba.first_rest.service;

import java.util.List;

import org.izba.first_rest.databas.AlbumsDB;
import org.izba.first_rest.enums.AlbumSql;
import org.izba.first_rest.exception.DataNotFoundException;
import org.izba.first_rest.model.Album;


public class AlbumService {
	
	private AlbumsDB albumDB = new AlbumsDB();
	
	public List<Album> getAllAlbums() {
		String sql = AlbumSql.SQL.toString();
		List<Album> albums = albumDB.listAlbums(sql);
		if (albums.size() <= 0){
			throw new DataNotFoundException("Not found records");
		}
		return albums;
	}
	
	public List<Album> getAlbumById(Long id, long userId) {
		String sql = AlbumSql.SQL.toString();
		if (id > 0 && userId <= 0) {
			sql = sql + " where id = " + id;
		} else if (id <= 0 && userId > 0) {
			sql = sql + " where userId = " + userId;
		} else if (id > 0 && userId > 0) {
			sql = sql + " where id = " + id + " or userId = " + userId;
		}
		List<Album> albums = albumDB.listAlbums(sql);
		if (albums.size() <= 0){
			throw new DataNotFoundException("Not found record with id: " + id);
		}
		return albums;
	}
	
	public Album addAlbum(Album album) {
		String sql = AlbumSql.SQL_INSERT.toString();
		return albumDB.insertUpdateAlbum(album, sql, true);
	}
	
	public Album updateAlbum(Album album) {
		if (album.getId() <= 0) {
			return null;
		}
		String sql = AlbumSql.SQL_UPDATE.toString() + album.getId(); 
		return albumDB.insertUpdateAlbum(album, sql, false);	
	}
	
	public void removeAlbum(Long id) {
		String sql = AlbumSql.SQL_DELETE.toString();
		albumDB.deleteAlbum(sql, id);
	}

}
