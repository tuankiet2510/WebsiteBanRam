package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnect {
	public Connection getConnection() throws SQLException, ClassNotFoundException {
        /*
		String url = "jdbc:sqlserver://35.220.233.228;databaseName=quanlybanram;user=sqlserver;password=12345;cloudSqlInstance=java-cloud-app-407108:asia-east2:banram;encrypt=false;trustServerCertificate=true";
		//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url);
        */
        //String url = "jdbc:sqlserver://35.220.233.228;databaseName=quanlybanram;cloudSqlInstance=java-cloud-app-407108:asia-east2:banram;encrypt=false;trustServerCertificate=true";
		String url = "jdbc:sqlserver://35.220.233.228;database=quanlybanram;"
        + "cloudSqlInstance=java-cloud-app-407108:asia-east2:banram;"
        + "socketFactory=com.google.cloud.sql.sqlserver.SocketFactory";
        Connection conn = null;
		//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			conn = DriverManager.getConnection(url,"sqlserver","12345");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		try {
			System.out.println(new DBConnect().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
