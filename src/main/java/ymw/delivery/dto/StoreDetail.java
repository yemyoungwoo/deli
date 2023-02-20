package ymw.delivery.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StoreDetail {
	@Setter @Getter
	private Store storeInfo;
	private List<Food> foodList;
	private List<Review> reviewList;
}