//package ymw.delivery.login;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LoginSuccess implements AuthenticationSuccessHandler {
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		
//		response.sendRedirect("/myPage");
//	}
//}