package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ProjectDao {
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String ID = "user01";
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
	
	// TODO 입력한 자료 저장
	public void insert(LegerVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into ledger" + 
					"	  values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getLedNo());
			pstmt.setString(2, vo.getLedName());
			pstmt.setInt(3, vo.getLedMoney());
			pstmt.setString(4, vo.getLedType());
			pstmt.setString(5, vo.getLedDay());
			pstmt.setString(6, vo.getLedMemo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	} // 입력한 자료 저장 insert
	// TODO 저장된값 불러오기(조회)
	public Vector<LegerVo> search(SearchDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String key = dto.getSearchKey();
//			System.out.println(key);
			String sql = "select *"
					+ "	  from ledger"
					+ "	  where " + key + " = ?";
//					+ "	  where " + key + " = (select " + key
//					+ "				   	       from ledger"
//					+ "				 		   where " + key + "= ?";
			pstmt = conn.prepareStatement(sql);
			if (key.equals("ledmoney")) {
				int val = Integer.parseInt(dto.getSearchTxt());
				pstmt.setInt(1, val);
			} else if (key.equals("ledname")) {
				String val = dto.getSearchTxt();
				pstmt.setString(1, val);
			} else if (key.equals("ledmemo")) {
				String val = "%" + dto.getSearchTxt() + "%";
				pstmt.setString(1, val);
			}
			rs = pstmt.executeQuery();
//			System.out.println(rs);
			Vector<LegerVo> vec = new Vector<>();
			
			while(rs.next()) {
				int ledNo = rs.getInt("ledNo");
				String ledName = rs.getString("ledName");
				int ledMoney = rs.getInt("ledMoney");
				String ledType = rs.getString("ledType");
				String ledDay = rs.getString("ledDay");
				String ledMemo = rs.getString("ledMemo");
				
				LegerVo vo = new LegerVo(ledNo, ledName, ledMoney, ledType, ledDay, ledMemo);
				vec.add(vo);
			}
			System.out.println(vec);
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	
	
	// TODO 저장된값 불러오기(통계)
	
	
	
	
} // class
