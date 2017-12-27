package org.izba.first_rest.enums;

public enum AlbumSql {
	SQL {
		public String toString() {
			String sql = "select * from albums";
			return sql;
		}
	},
	SQL_INSERT {
		public String toString() {
			String sql = "insert into albums (userId, title) values (?, ?)";
			return sql;
		}
	},
	SQL_UPDATE {
		public String toString() {
			String sql = "update albums set userId=?, title=? where id = ";
			return sql;
		}
	},
	SQL_DELETE {
		public String toString() {
			String sql = "delete from albums where id = ?";
			return sql;
		}
	}

}
