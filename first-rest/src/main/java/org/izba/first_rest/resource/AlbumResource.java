package org.izba.first_rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.izba.first_rest.model.Album;
import org.izba.first_rest.service.AlbumService;

@Path("/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumResource {
	
	private AlbumService albumService = new AlbumService();
	
	@GET
	public List<Album> getAlbums(@QueryParam("id") long id, @QueryParam("userId") long userId) {
		if (id > 0 || userId > 0) {
			return albumService.getAlbumById(id, userId);
		}
		return albumService.getAllAlbums();
	}

	@GET
	@Path("/{albumId}")
	public List<Album> getAlbum(@PathParam("albumId") long albumId) {
		return albumService.getAlbumById(albumId, 0);
	}
	
	@POST
	public Album addAlbum(Album album) {
		return albumService.addAlbum(album);
	}
	
	@PUT
	@Path("/{albumId}")
	public Album updateAlbum(@PathParam("albumId") long albumId, Album album) {
		album.setId(albumId);
		return albumService.updateAlbum(album);
	}
	
	@DELETE
	@Path("/{albumId}")
	public void deleteAlbum(@PathParam("albumId") long albumId) {
		albumService.removeAlbum(albumId);
	}

}
