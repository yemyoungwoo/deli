package ymw.delivery.dao;

import java.util.List;
import java.util.Map;

import ymw.delivery.dto.Food;
import ymw.delivery.dto.FoodOption;
import ymw.delivery.dto.Review;
import ymw.delivery.dto.Store;

public interface StoreDAO {

	List<Store> storeList(Map<String, Object> map);

	List<Food> foodList(long storeId);

	List<FoodOption> foodOption(int foodId);

	void reviewWrite(Review review);

	List<Review> reviewList(long id);

	void reviewModify(Review review);

//	void reviewDelete(Review review);
	
	void addLikes(Map<String, Long> map);

	void deleteLikes(Map<String, Long> map);
	
//	Store storeDetail(long storeId, long userId);

	List<Store> likesList(long userId);

	Store storeDetail(long storeId, long userId);

	List<Store> storeSearch(Map<String, Object> map);
}