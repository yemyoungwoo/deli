package ymw.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ymw.delivery.dao.UserDAO;
import ymw.delivery.dto.Join;

@Service
public class UserServicelmp implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void join(Join join) {
		userDAO.join(join);
	}
	

	@Override
	public int overlapCheck(String value, String valueType) {
		return userDAO.overlapCheck(value, valueType);
	}

}