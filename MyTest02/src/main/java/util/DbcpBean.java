package util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// DB 와 연결 기능
public class DbcpBean {
	// Connection 객체를 리턴해주는 메소드
	public Connection getConn() {
		Connection conn = null;
		
		// Tomcat 서버가 관리하는 Connection 객체를 얻어 필드에 저장
		try {
			Context initContext = new InitialContext();
			
			// Server/context.xml 문서에 설정된 jdbc/myoracle 이라는 이름의 datatsource를 얻어옴
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			// 얻어온 datasource 객체를 이용해서 Connection 객체의 참조값을 얻어와서 지역변수에 저장
			DataSource ds = (DataSource)envContext.lookup("jdbc/mytest-oracle");
			
			conn = ds.getConnection();
			
			//예외가 발생하지 않고 여기까지 실행의 흐름이 넘어온다면 성공
			System.out.println("Connection 얻어오기 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
