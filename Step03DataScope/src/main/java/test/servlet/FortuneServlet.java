package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오늘의 운세를 얻어오는 비즈니스 로직을 수행(DB에서 가져왔다고 가정)
		String fortune = "동쪽으로 가면 귀인을 만나요!";
		
		// 오늘의 운세를 request scope 에 담는다.
		// "fortuneToday" 라는 키값으로 String type 담기
		request.setAttribute("fortuneToday", fortune);
		
		// 응답은 JSP 페이지에 위임한다.(forward 이동)
		// .getRequestDispatcher("응답을 위임할 JSP 페이지 위치")
		RequestDispatcher rd = request.getRequestDispatcher("/test/fortune.jsp");
		rd.forward(request, response);
		
		
		
		/*
		 * 여기서 html 형식으로 오늘의 운세를 응답 하려면 머리가 아프다
		 * webapp/ 에 어딘가 있는 JSP 페이지에게 대신 응답하라고 
		 * 응답을 위임할 수가 있다.
		 * 대신 응답에 필요한 데이터(운세)는 request scope 에 담아서
		 * 전달해 주어야 한다.
		 */
		
	}
}
