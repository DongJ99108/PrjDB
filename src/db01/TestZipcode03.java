package db01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestZipcode03 {
	
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url    = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String dbuid  = "hr";
	private static String dbpwd  = "1234";


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		// 부서명, 직원이름, 전화번호
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		Statement  stmt = conn.createStatement();
		String     sql  = 
				" SELECT D.DEPARTMENT_NAME, E.FIRST_NAME || ' ' || E.LAST_NAME, E.PHONE_NUMBER "
				+ " FROM EMPLOYEES E FULL JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID "
				+ "ORDER BY D.DEPARTMENT_NAME";
		
		System.out.println( sql );
		ResultSet rs = stmt.executeQuery(sql);
		
		while ( rs.next() != false ) {
			System.out.print( rs.getString(1) + "," );
			System.out.print( rs.getString(2) + "," );
			System.out.print( rs.getString(3) + "," );
			System.out.println();
		}
		
		
		
		
		rs.close();
		stmt.close();
		conn.close();

	}

}
