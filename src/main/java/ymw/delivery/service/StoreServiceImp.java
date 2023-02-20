package ymw.delivery.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ymw.delivery.dao.StoreDAO;
import ymw.delivery.dto.Food;
import ymw.delivery.dto.FoodOption;
import ymw.delivery.dto.Review;
import ymw.delivery.dto.Store;
import ymw.delivery.dto.StoreDetail;
import ymw.delivery.util.Page;

@Service
public class StoreServiceImp implements StoreService {
	@Autowired
	private StoreDAO storeDAO;

//	@Override
//	public List<Store> storeList(int category, int address) {
//		return storeList(category, address, "주문접수 대기 중", 1);
//	}


	@Override
	public List<Store> storeList(int category, int address) {
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("address1", address);
		
		return storeDAO.storeList(map);
	}

	@Override
	public List<FoodOption> foodOption(int foodId) {
		return storeDAO.foodOption(foodId);
	}


	@Override
	public void reviewWrite(Review review) {
		storeDAO.reviewWrite(review);
	}

	@Override
	public void reviewModify(Review review) {
	    storeDAO.reviewModify(review);
	}
	
//	@Override
//	public void reviewDelete(Review review) {
//		storeDAO.reviewDelete(review);
//	}


	@Override
	public void likes(long storeId, String likes, long userId) {
		Map<String, Long> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("userId", userId);

		if(likes.equals("on")) {
			storeDAO.addLikes(map);
		} else {
			storeDAO.deleteLikes(map);
		}

	}
	@Override
	public StoreDetail storeDetail(long storeId, long userId) {
		Store storeInfo = storeDAO.storeDetail(storeId, userId); 
		List<Food> foodList = storeDAO.foodList(storeId);
		List<Review> reviewList = storeDAO.reviewList(storeId);

		return new StoreDetail(storeInfo, foodList, reviewList);
	}
	
	@Override
	public List<Store> likesList(long userId) {
		return storeDAO.likesList(userId);
	}

	@Override
	public List<Store> storeList(int category, int address, String sort, int page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Store> storeSearch(String keyword, int address, Page p) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("keyword", keyword);
	    map.put("address", address);
	    map.put("firstList", p.getFirstList());
	    map.put("lastList", p.getLastList());
	    
	    return storeDAO.storeSearch(map);
	}
}