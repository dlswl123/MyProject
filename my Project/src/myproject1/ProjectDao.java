package myproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectDao {
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String ID = "tester03";
	private final String PW = "1234";
	
	private static ProjectDao instance;
	
	private ProjectDao () {}
	
	public static ProjectDao getInstance() {
		if (instance == null) {
			instance = new ProjectDao();
		}
		return instance;
	} // getInstence
	
	private Connection getConnection() {
		try {
			// 드라이버를 메모리에 로드
			Class.forName(DRIVER); // 
			Connection conn = DriverManager.getConnection(URL, ID, PW); // conn id/pw -> 아이디와 패스워드입력
//			System.out.println("conn : " + conn);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 에러나면 null 리턴
	} // getConnection
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)    try {rs.close();}    catch (Exception e) { }
		if (pstmt != null) try {pstmt.close();} catch (Exception e) { }
		if (conn != null)  try {conn.close();}  catch (Exception e) { }
	} // closeAll
	
	
	
} // class
