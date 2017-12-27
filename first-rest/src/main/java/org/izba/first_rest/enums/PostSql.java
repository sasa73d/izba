package org.izba.first_rest.enums;

public enum PostSql {
	SQL {
		public String toString() {
			String sql = "select * from posts";
			return sql;
		}
	},
	SQL_INSERT {
		public String toString() {
			String sql = "insert into posts ( userId, list_title, list_body) values (?, ?, ?)";
			return sql;
		}
	},
	SQL_UPDATE {
		public String toString() {
			String sql = "update posts set userId=?, list_title=?, list_body=? where id = ";
			return sql;
		}
	},
	SQL_DELETE {
		public String toString() {
			String sql = "delete from posts where id = ?";
			return sql;
		}
	}
}
