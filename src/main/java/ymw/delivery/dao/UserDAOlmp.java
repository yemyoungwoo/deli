package ymw.delivery.dao;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;

@Repository
public class UserDAOlmp implements UserDAO {

	@Autowired
	private SqlSession sql;

	@Override
	public void join(Join join) {
		sql.insert("user.join" , join);
	}

	@Override
	public int overlapCheck(String value, String valueType) {
		Map<String, String> map = new HashMap<>();
		map.put("value", value);
		map.put("valueType", valueType);

		return sql.selectOne("user.overlapCheck" ,map);
	}
	
//	@Override
//	public List<Point> myPoint(long id) {
//		return sql.selectList("user.myPoint", id);
//	}
	
	@Override
	public List<Review> myReviewList(long id) {
		return sql.selectList("user.myReviewList", id);
	}

	@Override
	public void deleteReview(Map<String, Object> map) {
		sql.delete("user.deleteReview", map);
		
	}

	@Override
	public List<Point> myPoint(long id) {
		return sql.selectList("user.myPoint", id);
	}
	
	@Override
	public void modifyInfo(Map<String, Object> map) {
	    sql.update("user.modifyInfo", map);
	}
}