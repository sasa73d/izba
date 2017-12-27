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

import org.izba.first_rest.model.User;
import org.izba.first_rest.service.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	UserService userService = new UserService();
	
	@GET
	public List<User> getUsers(@QueryParam("id") long userId) {
		if (userId > 0) {
			return userService.getUserById(userId);
		}
		return userService.getAllUsers();
	}
	
	@GET
	@Path("/{userId}")
	public List<User> getUser(@PathParam("userId") long userId) {
		return userService.getUserById(userId);
	}
	
	@POST
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") long userId, User user) {
		user.setId(userId);;
		return userService.updateUser(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long userId) {
		userService.removeUser(userId);
	}
}
