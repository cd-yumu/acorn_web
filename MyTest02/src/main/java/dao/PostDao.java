package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.PostDto;
import util.DbcpBean;

public class PostDao {
	// static 영역에 dao 필드 생성
	private static PostDao dao;
	// static 영역에 dao 객체 생성
	static {
		dao = new PostDao();
	}
	// static PostDao 생성자
	private PostDao() {};
	// 외부에서 dao 를 참조할 수 있는 메소드
	public static PostDao getInstance() {
		return dao;
	}
	
	// 글 삭제
    public boolean deletePost(int num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
        	// DB Connect
            conn = new DbcpBean().getConn();
            // 사용할 SQL 문
            String sql = """
                DELETE FROM posts
                WHERE num=?
            """;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            // SQL 문 실행
            rowCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowCount > 0;
    }
	
	// 글 수정
	public boolean updatePost(PostDto dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
        	// DB Connect
            conn = new DbcpBean().getConn();
            // 사용할 SQL 문
            String sql = """
                UPDATE posts
                SET title=?, content=?, updatedAt=SYSDATE
                WHERE num=?
            """;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setLong(3, dto.getNum());
            // SQL 문 실행
            rowCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowCount > 0;
    }
	
	// 글 추가
	public Boolean addPost(PostDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//DB Connect
			conn = new DbcpBean().getConn();

			// 사용할 SQL 문
			String sql = """
					INSERT INTO POSTS
					(NUM, WRITER, TITLE, CONTENT)
					VALUES (POSTS_SEQ.NEXTVAL, ?, ?, ?)
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());

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
	
	// 글 하나 정보 추출
	public PostDto getData(int num) {
		PostDto dto = new PostDto();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//DB Connect
			conn = new DbcpBean().getConn();

			// 사용할 SQL 문
			String sql = """
					SELECT writer, title, content, viewcount, createdAt, updatedAt
					FROM POSTS
					WHERE num = ?
					ORDER BY NUM DESC
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			// SQL 문 실행
			rs = pstmt.executeQuery();

			// 얻은 데이터를 DTO 에 담아 LIST 구성
			while (rs.next()) {
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewCount(rs.getInt("viewcount"));
				dto.setCreatedAt(rs.getString("createdAt"));
				dto.setUpdatedAt(rs.getString("updatedAt"));
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

		return dto;
	}
	
	
	// 글 목록 추출
	public List<PostDto> getList(){
		List<PostDto> list = new ArrayList<PostDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//DB Connect
			conn = new DbcpBean().getConn();
			
			// 사용할 SQL 문
			String sql = """
				SELECT * 
				FROM POSTS
				ORDER BY NUM DESC
				""";
			pstmt = conn.prepareStatement(sql);
			
			// SQL 문 실행
			rs = pstmt.executeQuery();
			
			// 얻은 데이터를 DTO 에 담아 LIST 구성
			while(rs.next()) {
				PostDto dto = new PostDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewCount(rs.getInt("viewcount"));
				dto.setCreatedAt(new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate("createdAt")));
				dto.setUpdatedAt(new SimpleDateFormat("yyyy년 MM월 dd일").format(rs.getDate("updatedAt")));
				list.add(dto);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		return list;
	}
}
