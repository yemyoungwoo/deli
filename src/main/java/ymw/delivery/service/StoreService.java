package ymw.delivery.service;

import java.util.List;

import ymw.delivery.dto.FoodOption;
import ymw.delivery.dto.Store;
import ymw.delivery.dto.StoreDetail;


	public interface StoreService {
		List<Store> storeList(int category, int address);

		StoreDetail storeDetail(long id);
		
		List<FoodOption> foodOption(int foodId);
	}
	
