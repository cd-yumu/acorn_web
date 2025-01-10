package test.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.guest.dto.GuestDto;
import test.util.DbcpBean;

/*
 * application 전역에서 GuestDao 객체는 오직 한 개만 생성되어서 사용되도록 클래스를 설계한다.
 * - 한정된 자원인 Connection 객체를 좀 더 효율적으로 사용하기 위해
 * 
 */

public class GuestDao {

	// 자신의 참조값을 저장할 static 필드
	private static GuestDao dao;

	// static 초기화 블럭 (이 클래스가 최초로 사용될 때 오직 한 번만 수행된다.)
	static {
		// 객체를 생성해서 static 필드에 담는다.
		dao = new GuestDao();
	}

	// 외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 설정
	private GuestDao() {
	};

	// static 필드에 저장된 GuestDao 의 참조값을 리턴해주는 static 메소드
	public static GuestDao getInstance() {
		return dao;
	}

	// 하나만 불러오기
	public GuestDto getData(int num) {

		GuestDto dto = new GuestDto();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();

			// 실행할 SQL 문 작성
			String sql = """
					SELECT WRITER, CONTENT, PWD, REGDATE
					FROM BOARD_GUEST
					WHERE NUM = ?
					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할 게 있으면 여기서 하기
			pstmt.setInt(1, num);

			// sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNum(num);
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPwd(rs.getString("PWD"));
				dto.setRegdate(rs.getString("REGDATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return dto;
	}

	// 모두 불러오기
	public List<GuestDto> getList() {

		List<GuestDto> list = new ArrayList<GuestDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();

			// 실행할 SQL 문 작성
			String sql = """
					SELECT NUM, WRITER, CONTENT, PWD, REGDATE
					FROM BOARD_GUEST
					ORDER BY NUM DESC
					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할 게 있으면 여기서 하기

			// sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestDto dto = new GuestDto();
				dto.setNum(rs.getInt("NUM"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPwd(rs.getString("PWD"));
				dto.setRegdate(rs.getString("REGDATE"));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return list;
	}

	// 수정
	public boolean update(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					UPDATE BOARD_GUEST
					SET WRITER=?, CONTENT=?
					WHERE NUM = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());

			// sql 문 실행 후 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		if (rowCount > 0)
			return true;
		else
			return false;
	}

	// 삭제
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					DELETE FROM BOARD_GUEST
					WHERE NUM = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setInt(1, num);

			// sql 문 실행 후 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		if (rowCount > 0)
			return true;
		else
			return false;
	}

	// 추가
	public boolean insert(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					INSERT INTO BOARD_GUEST
					(NUM, WRITER, CONTENT, PWD, REGDATE)
					VALUES(BOARD_GUEST_SEQ.NEXTVAL, ?,?,?,SYSDATE)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPwd());

			// sql 문 실행 후 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		if (rowCount > 0)
			return true;
		else
			return false;
	}
}

/*
 * GuestDao dao = new GuestDao(); X GuestDao dao = GuestDao.getInstance() O
 */
