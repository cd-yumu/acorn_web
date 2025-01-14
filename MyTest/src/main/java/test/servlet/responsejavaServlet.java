package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/responsejava")
public class responsejavaServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		// 응답 컨텐트 설정
		resp.setContentType("text/html; charset=utf-8");

		PrintWriter pw = resp.getWriter();
		pw.println("<p>요청을 responsejava 에게 했다.</p>");
		pw.println("<p>responsejavaServlet.java 파일의 서블릿이 응답한다.</p>");
		pw.println("*<p>responsejavaServlet.java 파일에 어노테이션으로 \"/responsejava\"설정이 되어 있기 때문에</p>");
		
	}
}
