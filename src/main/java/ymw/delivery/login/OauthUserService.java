package ymw.delivery.login;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.User;
import ymw.delivery.dao.UserDAO;

@Service
public class OauthUserService extends DefaultOAuth2UserService {

	@Autowired
	private SqlSession sql;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder encodePwd;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2user = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		String username = provider + "_" + oauth2user.getAttribute("sub"); 
		
		User user = sql.selectOne("user.login", username);
		
		if(user == null) {
			// 회원가입
			UUID uid = UUID.randomUUID();
			String password = encodePwd.encode(uid.toString());
			String email = oauth2user.getAttribute("email");
			String phone = oauth2user.getAttribute("phone") == null ? "" : oauth2user.getAttribute("phone");
			
			Join join = new Join();
			join.setUsername(username);
			join.setPassword(password);
			join.setEmail(email);
			join.setNickname(username);
			join.setPhone(phone);
			
			userDAO.join(join);
			
			user = sql.selectOne("user.login", username);
		}
 		
		LoginService loginService = new LoginService();
		loginService.setUser(user);
		
		return loginService;
	}
}