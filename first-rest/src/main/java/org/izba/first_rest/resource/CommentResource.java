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

import org.izba.first_rest.model.Comment;
import org.izba.first_rest.service.CommentService;

@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService= new CommentService();
	
	@GET
	public List<Comment> getComments(@QueryParam("id") long id, @QueryParam("postId") long postId) {
		if (id > 0 || postId > 0) {
			return commentService.getCommentById(id, postId);
		}
		return commentService.getAllComments();
	}
	
	@GET
	@Path("/{commentId}")
	public List<Comment> getComment(@PathParam("commentId") long commentId) {
		return commentService.getCommentById(commentId, 0);
	}
	
	@POST
	public Comment addComment(Comment comment) {
		return commentService.addComment(comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("commentId") long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId") long commentId) {
		commentService.removeComment(commentId);
	}

}
