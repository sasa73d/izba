package org.izba.first_rest.enums;

public enum UserSql {

	SQL {
		public String toString() {
			String sql = "select * from users";
			return sql;
		}
	},
	SQL_WHERE {
		public String toString() {
			String sql = "select * from users where id = ";
			return sql;
		}
	},
	SQL_INSERT {
		public String toString() {
			String sql = "insert into users (name, username" + 
			", email, address_street, address_suite, address_city, address_zipcode" + 
			", address_geo_lat, address_geo_lng, phone, website, company_name, company_catchPhrase, company_bs)" + 
			" values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			return sql;
		}
	},
	SQL_UPDATE {
		public String toString() {
			String sql = "update users set name=?, username=?, email=?" + 
			", address_street=?, address_suite=?, address_city=?, address_zipcode=?, address_geo_lat=?" + 
			", address_geo_lng=?, phone =?, website=?, company_name=?, company_catchPhrase=?, company_bs=? WHERE id = ";
			return sql;
		}
	},
	SQL_DELETE {
		public String toString() {
			String sql = "delete from users where id = ?";
			return sql;
		}
	};
	
}
