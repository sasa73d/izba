package org.izba.first_rest.service;

import java.util.List;

import org.izba.first_rest.databas.CommentDB;
import org.izba.first_rest.enums.CommentSQL;
import org.izba.first_rest.exception.DataNotFoundException;
import org.izba.first_rest.model.Comment;

public class CommentService {
	private CommentDB commentDB = new CommentDB();
	
	public List<Comment> getAllComments() {
		String sql = CommentSQL.SQL.toString();
		List<Comment> comments = commentDB.listComments(sql);
		if (comments.size() <= 0){
			throw new DataNotFoundException("Not found records");
		}
		return comments;
	}
	
	public List<Comment> getCommentById(Long id, long postId) {
		String sql = CommentSQL.SQL.toString();
		if (id > 0 && postId <= 0) {
			sql = sql + " where id = " + id;
		} else if (id <= 0 && postId > 0) {
			sql = sql + " where postId = " + postId;
		} else if (id > 0 && postId > 0) {
			sql = sql + " where id = " + id + " or postId = " + postId;
		}
		List<Comment> comments = commentDB.listComments(sql);
		if (comments.size() <= 0){
			throw new DataNotFoundException("Not found record with id: " + id);
		}
		return comments;
	}
	
	public Comment addComment(Comment comment) {
		String sql = CommentSQL.SQL_INSERT.toString();
		return commentDB.insertUpdateComment(comment, sql, true);
	}
	
	public Comment updateComment(Comment comment) {
		if (comment.getId() <= 0) {
			return null;
		}
		String sql = CommentSQL.SQL_UPDATE.toString() + comment.getId(); 
		return commentDB.insertUpdateComment(comment, sql, false);	
	}
	
	public void removeComment(Long id) {
		String sql = CommentSQL.SQL_DELETE.toString();
		commentDB.deleteComment(sql, id);
	}
}
