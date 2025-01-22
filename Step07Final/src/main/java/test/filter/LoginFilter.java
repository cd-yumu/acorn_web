package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.user.dto.SessionDto;

@WebFilter({"/member-only/*", "/staff/*", "/admin/*", "/user/protected/*"})
public class LoginFilter implements Filter{
	
	// @WebFilter()에 명시한 패턴의 요청을 하면 아래의 메소드가 호출된다.
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		
		// 매개변수에 전달된 객체를 이용해서 자식 타입 객체의 참조값을 얻어낸다.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(); 
        // 부모타입에는 세션을 얻는 메소드가 없어서 자식 타입으로 캐스팅 한 후 진행한다.

        // 세션 영역에서 로그인된 정보를 얻어내기 위한 객체
        SessionDto dto = (SessionDto) session.getAttribute("sessionDto");

        // 만일 로그인을 하지 않았다면
        if (dto == null) { 
        	// 로그인 페이지로 리다이렉트 시키는 메소드를 호출한다.
            redirectToLogin(req, res);
            return;
        }   
        // "리다이렉트는 다른 경로로 요청을 다시 하라고 강제하는 것이다." - 이것 자체가 응답이다.
        // 참고로 '포워드'는 응답을 위임하는 것.
        
        // Role-based authorization
        // role 을 확인해서 /admin/*, /staff/* 요청도 필터링 해준다.
        String role = dto.getRole();
        String requestURI = req.getRequestURI();
       
        // 너의 role 이 admin 도 아니면서 admin 경로를 가려고 한다면
        if (requestURI.startsWith(req.getContextPath() + "/admin") && !"ADMIN".equals(role)) {
        	// 금지된 요청이라고 응답한다.
        	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }
        // 너의 role 이 staff 도 아니고 admin 도 아니면서 staff 경로로 가려고 한다면
        if (requestURI.startsWith(req.getContextPath() + "/staff") && !"STAFF".equals(role) && !"ADMIN".equals(role)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }

        // 여기까지 실행의 흐름이 넘어오면 흐름을 계속 이어간다.
        // Allow access for USER, STAFF, ADMIN
        chain.doFilter(request, response);
    }

	// 리다이렉트 - 요청을 새로운 경로로 다시 하라는 응답하는 메소드
    private void redirectToLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 원래 요청 url 을 읽어온다
    	String url = req.getRequestURI();
    	// 혹시 뒤에 query paramter 가 있다면 그것 역시 읽어온다. ex) ?num=xxx&count=xxx...
        String query = req.getQueryString(); 			// 쿼리 파라미터를 제대로 읽어오기 위해 인코딩이 필요하다
        String encodedUrl = query == null ? URLEncoder.encode(url, "UTF-8") : URLEncoder.encode(url + "?" + query, "UTF-8");
        // 로그인 페이지로 리다이렉트 이동하면서 원래 가려던 목적지 정보도 같이 넘겨준다.
        res.sendRedirect(req.getContextPath() + "/user/login-form.jsp?url=" + encodedUrl);
        // url 있다면 원래 가던 곳으로 보내줘야 한다. 
    }
	
}
