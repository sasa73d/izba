package org.izba.first_rest.service;

import java.util.List;

import org.izba.first_rest.databas.PostDB;
import org.izba.first_rest.enums.PostSql;
import org.izba.first_rest.exception.DataNotFoundException;
import org.izba.first_rest.model.Post;

public class PostService {
	
	private PostDB postDB = new PostDB();
	
	public List<Post> getAllPosts() {
		String sql = PostSql.SQL.toString();
		List<Post> posts = postDB.listPosts(sql);
		if (posts.size() <= 0){
			throw new DataNotFoundException("Not found records");
		}
		return posts;
	}
	
	public List<Post> getPostById(Long id, long userId) {
		String sql = PostSql.SQL.toString();
		if (id > 0 && userId <= 0) {
;			sql = sql + " where id = " + id;
		} else if (id <= 0 && userId > 0) {
			sql = sql + " where userId = " + userId;
		} else if (id > 0 && userId > 0) {
			sql = sql + " where id = " + id + " or userId = " + userId;
		}
		List<Post> posts = postDB.listPosts(sql);
		if (posts.size() <= 0){
			throw new DataNotFoundException("Not found record with id: " + id);
		}
		return posts;
	}
	
	public Post addPost(Post post) {
		String sql = PostSql.SQL_INSERT.toString();
		return postDB.insertUpdatePost(post, sql, true);
	}
	
	public Post updatePost(Post post) {
		if (post.getId() <= 0) {
			return null;
		}
		String sql = PostSql.SQL_UPDATE.toString() + post.getId(); 
		return postDB.insertUpdatePost(post, sql, false);	
	}
	
	public void removePost(Long id) {
		String sql = PostSql.SQL_DELETE.toString();
		postDB.deletePost(sql, id);
	}

}
