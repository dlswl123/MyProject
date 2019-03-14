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
			// ����̹��� �޸𸮿� �ε�
			Class.forName(DRIVER); // 
			Connection conn = DriverManager.getConnection(URL, ID, PW); // conn id/pw -> ���̵�� �н������Է�
//			System.out.println("conn : " + conn);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // �������� null ����
	} // getConnection
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)    try {rs.close();}    catch (Exception e) { }
		if (pstmt != null) try {pstmt.close();} catch (Exception e) { }
		if (conn != null)  try {conn.close();}  catch (Exception e) { }
	} // closeAll
	
	// TODO �Է��� �ڷ� ����
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
	} // �Է��� �ڷ� ���� insert
	// TODO ����Ȱ� �ҷ�����(��ȸ)
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
	
	
	
	// TODO ����Ȱ� �ҷ�����(���)
	
	
	
	
} // class
