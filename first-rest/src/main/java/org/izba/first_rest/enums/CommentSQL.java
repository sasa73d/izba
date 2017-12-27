package org.izba.first_rest.enums;

public enum CommentSQL {
	SQL {
		public String toString() {
			String sql = "select * from comments";
			return sql;
		}
	},
	SQL_INSERT {
		public String toString() {
			String sql = "insert into comments (postId, name, email, body) values (?, ?, ?, ?)";
			return sql;
		}
	},
	SQL_UPDATE {
		public String  toString() {
			String sql = "update comments set postId=?, name=?, email=?, body=? where id =";
			return sql;
		}
	},
	SQL_DELETE {
		public String toString() {
			String sql = "delete from comments where id = ?";
			return sql;
		}
	}

}
