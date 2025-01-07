package test.survlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 3. 어떤 요청이 왔을 때 이 클래스로 생성된 객체로 응답을 할 지 정해야 한다.
@WebServlet("/ping")
//이 클래스로 생성된 객체가 tomcat 서버에서 /ping 요청이 오면 직접 응답을 하도록...
public class PingServlet extends HttpServlet{	// 1. HttpServlet 클래스를 상속 받는다.
	
	// 2. service() 메소드를 오버라이딩 한다.
	@Override
//	/ping 요청이 오면 이 메소드가 실행된다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에게 문자열을 출력할 수 있는 객체를 얻어낸다.
		PrintWriter pw = response.getWriter();
//		웹 클라이언트에게 출력
		// 이 객체를 이용해서 출력하는 문자열은 요청을 한 클라이언트에게 출력된다
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>나의 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>pong!</h1>");
		pw.println("</body>");
		pw.println("</html>");
//		pw.println("pong!");
		pw.close();
		
	}
	
}
