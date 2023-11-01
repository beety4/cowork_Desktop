package cowork_Desktop.domain;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql {
	public static Connection getConnection() {
		try {
			String DBurl = "jdbc:mysql://akotis.kr:3306/cowork";
			String DBid = "coworkroot";
			String DBpw = "pw.1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DBurl, DBid, DBpw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
