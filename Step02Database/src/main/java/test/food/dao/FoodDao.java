package test.food.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.food.dto.FoodDto;
import test.util.DbcpBean;

public class FoodDao {
	
	// 하나의 리스트를 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					DELETE FROM FOOD
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
	
	// 하나의 리스트를 수정하는 메소드
	public boolean update(FoodDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					UPDATE FOOD
					SET TYPE=?, NAME=?, PRICE=?
					WHERE NUM = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getNum());

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
	
	// 하나의 리스트를 가져오는 메소드
	public FoodDto getData(int num) {

		FoodDto dto = new FoodDto();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();

			// 실행할 SQL 문 작성
			String sql = """
					SELECT TYPE, NAME, PRICE
					FROM FOOD
					WHERE NUM = ?
					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할 게 있으면 여기서 하기\
			pstmt.setInt(1, num);

			// sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				dto.setNum(num);
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
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

	// 새 맛집 리스트를 추가하는 메소드
	public boolean insert(FoodDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			// 실행할 미완성 SQL문
			String sql = """
					INSERT INTO FOOD
					(NUM, TYPE, NAME, PRICE)
					VALUES (FOOD_SEQ.NEXTVAL, ?, ?, ?)
										""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());

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

	// 맛집 리스트 가져오는 메소드
	public List<FoodDto> getLsit() {

		List<FoodDto> list = new ArrayList<FoodDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();

			// 실행할 SQL 문 작성
			String sql = """
					SELECT NUM, TYPE, NAME, PRICE
					FROM FOOD
					ORDER BY NUM ASC
					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩할 게 있으면 여기서 하기

			// sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodDto dto = new FoodDto();
				dto.setNum(rs.getInt("num"));
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
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
}
