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

import org.izba.first_rest.model.Post;
import org.izba.first_rest.service.PostService;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
	
	private PostService postService = new PostService();
	
	@GET
	public List<Post> getPosts(@QueryParam("id") long id, @QueryParam("userId") long userId) {
		if (id > 0 || userId > 0) {
			return postService.getPostById(id, userId);
		}
		return postService.getAllPosts();
	}
	
	@GET
	@Path("/{postId}")
	public List<Post> getPost(@PathParam("postId") long postId) {
		return postService.getPostById(postId, 0);
	}
	
	@POST
	public Post addPost(Post post) {
		return postService.addPost(post);
	}
	
	@PUT
	@Path("/{postId}")
	public Post updatePost(@PathParam("postId") long postId, Post post) {
		post.setId(postId);
		return postService.updatePost(post);
	}
	
	@DELETE
	@Path("/{postId}")
	public void deletePost(@PathParam("postId") long postId) {
		postService.removePost(postId);
	}
}
