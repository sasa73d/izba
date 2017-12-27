package org.izba.first_rest.enums;

public enum PhotoSql {
	SQL {
		public String toString() {
			String sql = "select * from photos";
			return sql;
		}
	},
	SQL_INSERT {
		public String toString() {
			String sql = "insert into photos (albumId, title, url, thumbnailUrl) values (?, ?, ?, ?)";
			return sql;
		}
	},
	SQL_UPDATE {
		public String toString() {
			String sql = "update photos set albumId=?, title=?, url=?, thumbnailUrl=? where id = ";
			return sql;
		}
	},
	SQL_DELETE {
		public String toString() {
			String sql = "delete from photos where id = ?";
			return sql;
		}
	}

}
