package ymw.delivery.service;

import java.util.List;

import ymw.delivery.dto.FoodOption;
import ymw.delivery.dto.Review;
import ymw.delivery.dto.Store;
import ymw.delivery.dto.StoreDetail;
import ymw.delivery.util.Page;


public interface StoreService {
	List<Store> storeList(int category, int address);

	List<Store> storeList(int category, int address, String sort, int page);

	List<FoodOption> foodOption(int foodId);

	void reviewWrite(Review review);

	void reviewModify(Review review);

//	void reviewDelete(Review review);

	void likes(long storeId, String likes, long userId);
	
	StoreDetail storeDetail(long id, long userId);

	List<Store> likesList(long userId);

	List<Store> storeSearch(String keyword, int address, Page p);
}