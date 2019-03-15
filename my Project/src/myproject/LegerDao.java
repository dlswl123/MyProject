package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class LegerDao {
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String ID = "user01";
	private final String PW = "1234";
	
	private static LegerDao instance;
	
	private LegerDao () {}
	
	public static LegerDao getInstance() {
		if (instance == null) {
			instance = new LegerDao();
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
	
	//  입력한 자료 저장
	public void insert(LegerVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into ledger " + 
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
	
	//  저장된값 불러오기(조회)
	public Vector<LegerVo> search(SearchDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String key = dto.getSearchKey();
			String array = dto.getSearchArray();
			System.out.println("search dao : " + array);
			String sql = "select *"
					+ "	  from ledger"
					+ "	  where " + key + " like ?"
					+ "   order by " + array ;
			
			pstmt = conn.prepareStatement(sql);
			if (key.equals("ledmoney")) {
				int val = Integer.parseInt(dto.getSearchTxt());
				pstmt.setInt(1, val);
			} else if (key.equals("ledname")) {
				String val = dto.getSearchTxt() + "%";
				pstmt.setString(1, val);
				System.out.println(val);
			} else if (key.equals("ledmemo")) {
				String val = "%" + dto.getSearchTxt() + "%";
				System.out.println(val);
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
	
	public Vector<LegerVo> searchAll(SearchDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String array = dto.getSearchArray();
//			System.out.println("projectdao, array : " + array);
			String sql = "select * from ledger"
					+ "   order by " + array ;
			
			pstmt = conn.prepareStatement(sql);
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
//			System.out.println("projectdao, vec : " + vec);
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	//  저장된값 불러오기(통계)
	
	public Vector<StatsVo> statsPieIncome() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select ledname, sum(ledmoney) ledmoney"
					+ "	  from ledger"
					+ "	  where ledno = 10" 
					+ "	  group by ledname"
					+ "   order by ledmoney desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<StatsVo> vec = new Vector<>();
			
			while(rs.next()) {
				String ledName = rs.getString("ledName");
				int ledMoney = rs.getInt("ledMoney");
				
				StatsVo vo = new StatsVo(ledName, ledMoney);
				vec.add(vo);
			}
//			System.out.println("projectdao, vec : " + vec);
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return null;
	} // statsPie() 통계작성하기
	
	public Vector<StatsVo> statsPieExpense() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select ledname, sum(ledmoney) ledmoney"
					+ "	  from ledger"
					+ "	  where ledno = 20" 
					+ "	  group by ledname"
					+ "   order by ledmoney desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<StatsVo> vec = new Vector<>();
			
			while(rs.next()) {
				String ledName = rs.getString("ledName");
				int ledMoney = rs.getInt("ledMoney");
				
				StatsVo vo = new StatsVo(ledName, ledMoney);
				vec.add(vo);
			}
//			System.out.println("projectdao, vec : " + vec);
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return null;
	}

	public Vector<LegerVo> Update() {
		
		return null;
	}
	
	// TODO 검색하기
	public int update(LegerVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update leger set"
					+ "		ledno = ?,"
					+ "		ledname = ?,"
					+ "		ledmoney = ?,"
					+ "		ledtype = ?"
					+ "		ledday = ?"
					+ "		ledmemo = ?"
					+ "	  where ledname = ?";
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			pstmt.setInt(++i, vo.getLedNo());
			pstmt.setString(++i, vo.getLedName());
			pstmt.setInt   (++i, vo.getLedMoney());
			pstmt.setString(++i, vo.getLedType());
			pstmt.setString(++i, vo.getLedDay());
			pstmt.setString(++i, vo.getLedMemo());
			pstmt.setString(++i, vo.getLedName());
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
			
		} // try~catch~finally
		return 0;
//		System.out.println("변경 실행됨");
	} // update
} // class
