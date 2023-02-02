//package ymw.delivery.login;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginDetailService implements UserDetailsService {
//
//	@Autowired
//	private SqlSession sql;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = sql.selectOne("user.login", username);
//
//		if (user != null) {
//			LoginService loginDetail = new LoginService();
//
//			loginDetail.setUser(user);
//
//			return loginDetail;
//		} else {
//			throw new UsernameNotFoundException("유저없음");
//		}
//	}
//
//}