package ymw.delivery.dao;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;

public interface UserDAO {
	void join(Join join);

	int overlapCheck(String value, String valueType);

	List<Review> myReviewList(long id);

	void deleteReview(Map<String, Object> map);

	List<Point> myPoint(long id);


//	List<Point> myPoint(long id);
}