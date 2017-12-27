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

import org.izba.first_rest.model.Photo;
import org.izba.first_rest.service.PhotoService;

@Path("/photos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource {
	
	private PhotoService photoService = new PhotoService();
	
	@GET
	public List<Photo> getPhotos(@QueryParam("id") long id, @QueryParam("albumId") long albumId) {
		if (id > 0 || albumId > 0) {
			return photoService.getPhotoById(id, albumId);
		}
		return photoService.getAllPhotos();
	}
	
	@GET
	@Path("/{photoId}")
	public List<Photo> getPhoto(@PathParam("photoId") long photoId) {
		return photoService.getPhotoById(photoId, 0);
	}
	
	@POST
	public Photo addPhoto(Photo photo) {
		return photoService.addPhoto(photo);
	}
	
	@PUT
	@Path("/{photoId}")
	public Photo updatePhoto(@PathParam("photoId") long photoId, Photo photo) {
		photo.setId(photoId);
		return photoService.updatePhoto(photo);
	}
	
	@DELETE
	@Path("/{photoId}")
	public void deletePhoto(@PathParam("photoId") long photoId) {
		photoService.removePhoto(photoId);
	}

	
}
