package ymw.delivery.service;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ymw.delivery.dao.UserDAO;
import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;

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
	
//	@Override
//	public List<Point> myPoint(long id) {
//		return userDAO.myPoint(id);
//	}
	
	@Override
	public List<Review> myReviewList(long id) {
		return userDAO.myReviewList(id);
	}


	@Override
	public void deleteReview(long id, String orderNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("orderNum", orderNum);
		userDAO.deleteReview(map);
	}


	@Override
	public List<Point> myPoint(long id) {
		return userDAO.myPoint(id);
	}
	
	@Override
	public void modifyInfo(String username, String valueType, String value) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("username", username);
	    map.put("valueType", valueType);
	    map.put("value", value);
	    userDAO.modifyInfo(map);
	}

}