package ymw.delivery.service;

import java.awt.Point;
import java.util.List;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;

public interface UserService {
	void join(Join join);

	int overlapCheck(String value, String valueType);

	List<Review> myReviewList(long id);

	void deleteReview(long id, String orderNum);

	List<Point> myPoint(long id);

	void modifyInfo(String username, String valueType, String value);

}
