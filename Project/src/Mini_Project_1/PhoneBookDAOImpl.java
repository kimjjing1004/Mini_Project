package Mini_Project_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOImpl implements PhoneBookDAO {
	//	공통 접속 메서드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//	드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbname = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbname,
					"C##BITUSER",
					"bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		
		return conn;
	}

	@Override
	public List<PhoneBookVO> getName() {
		Connection conn = null;
		Statement stmt = null;
		//	SELECT
		ResultSet rs = null;
		//	결과 객체
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 쿼리
			String sql = "SELECT id, name, hp, tel" +
						" FROM PHONE_BOOK";
			
			// 쿼리 실행
			rs = stmt.executeQuery(sql);
			
			//	ResultSet -> Java 객체로 변환
			while(rs.next()) {	//	다음 레코드가 있으면 반환
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs. getString(4);
				
				//	DTO 객체
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				//	DTO 객체 -> List에 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public List<PhoneBookVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PhoneBookVO> list = new ArrayList<>();
		
		String sql = "SELECT id, name, hp, tel FROM PHONE_BOOK " +
					" WHERE name LIKE ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			//	쿼리 수행
			rs = pstmt.executeQuery();
			
			//	변환
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				//	VO 객체
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public PhoneBookVO get(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PhoneBookVO vo = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM PHONE_BOOK " +
					" WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			rs = pstmt.executeQuery();	//	쿼리 실행
//			rs.next();	//	첫 번째 레코드?
			
			if (rs.next()) {
				Long num = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				vo = new PhoneBookVO(num, name, hp, tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return vo;		
	}

	@Override
	public boolean insert(PhoneBookVO vo) {
		//	.executeUpdate 메서드 -> int (삽입된 레코드 수)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			//	실행 계획
			String sql = "INSERT INTO PHONE_BOOK VALUES(SEQ_PHONE_BOOK_PK.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//	파라미터 바인딩
			pstmt.setString(1,  vo.getName());
			pstmt.setString(2,  vo.getHp());
			pstmt.setString(3,	vo.getTel());
			
			//	쿼리 수행
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == insertedCount;	//	삽입된 레코드가 1개면 성공
		
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == deletedCount;
	}

}
