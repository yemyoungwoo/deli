package ymw.delivery.dao;

import java.util.List;
import java.util.Map;

import ymw.delivery.dto.Food;
import ymw.delivery.dto.FoodOption;
import ymw.delivery.dto.Store;



public interface StoreDAO {

	List<Store> storeList(Map<String, Object> map);

	Store storeDetail(long storeId);
	
	List<Food> foodList(long storeId);
	
	List<FoodOption> foodOption(int foodId);
}