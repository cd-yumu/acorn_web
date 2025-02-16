package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.CommentDto;
import util.DbcpBean;

public class CommentDao {
	// static 영역에 dao 필드 생성
	private static CommentDao dao;
	// static 영역에 dao 객체 생성
	static {
		dao = new CommentDao();
	}
	// static PostDao 생성자
	private CommentDao() {};
	// 외부에서 dao 를 참조할 수 있는 메소드
	public static CommentDao getInstance() {
		return dao;
	}
	
	// 댓글 정보 추출 
	public List<CommentDto> getList(int num) {
		List<CommentDto> list = new ArrayList<CommentDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//DB Connect
			conn = new DbcpBean().getConn();

			// 사용할 SQL 문
			String sql = """
					SELECT *
					FROM COMMENTS
					WHERE POST_NUM = ?
					ORDER BY NUM DESC
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			// SQL 문 실행
			rs = pstmt.executeQuery();

			// 얻은 데이터를 DTO 에 담아 LIST 구성
			while (rs.next()) {
				CommentDto dto = new CommentDto();
				dto.setNum(rs.getInt("num"));
				dto.setParentNum(rs.getInt("parent_num"));
				dto.setPostNum(rs.getInt("post_num"));
				dto.setTargetWriter(rs.getString("target_writer"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setDeleted(rs.getString("deleted"));
				dto.setCreatedAt(new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate("createdAt")));
				dto.setUpdatedAt(new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate("updatedAt")));
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
				e.printStackTrace();
			}
		}

		return list;
	}
	
	// 댓글 추가
	public Boolean insert(CommentDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//DB Connect
			conn = new DbcpBean().getConn();

			// 사용할 SQL 문
			String sql = """
				INSERT INTO COMMENTS
				(NUM, PARENT_NUM, POST_NUM, TARGET_WRITER, WRITER, CONTENT)
				VALUES (?, ?, ?, ?, ?, ?)
				""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getParentNum());
			pstmt.setInt(2, dto.getPostNum());
			pstmt.setString(3, dto.getTargetWriter());
			pstmt.setString(4, dto.getWriter());
			pstmt.setString(5, dto.getContent());
			
			// SQL 문 실행
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
				e.printStackTrace();
			}
		}
		return rowCount > 0;
	}
	
	// 댓글 수정
	public boolean update(CommentDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
			    UPDATE COMMENTS
			    SET content=?, updatedAt=SYSDATE
			    WHERE num=?
				""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setLong(2, dto.getNum());
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
				e.printStackTrace();
			}
		}
		return rowCount > 0;
	}
	
	// 댓글 삭제
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					    DELETE FROM COMMENTS
					    WHERE num=?
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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
				e.printStackTrace();
			}
		}
		return rowCount > 0;
	}
}
