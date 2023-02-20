package ymw.delivery.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ymw.delivery.dto.Cart;
import ymw.delivery.dto.OrderDetail;
import ymw.delivery.dto.OrderInfo;
import ymw.delivery.dto.OrderList;

@Repository
public class OrderDAOImp implements OrderDAO {

	@Autowired
	private SqlSession sql;

	@Override
	public int getDeleveryTip(long storeId) {
		return sql.selectOne("order.getDeleveryTip", storeId);
	}

	@Override
	public List<Integer> foodPriceList(List<Cart> cartList) {
		return sql.selectList("order.foodPriceList", cartList);
	}

	@Override
	public List<Integer> optionPriceList(List<Cart> cart) {
		return sql.selectList("order.optionPriceList", cart);
	}
	
	@Override
	public void order(OrderInfo info) {
		sql.insert("order.order", info);
	}

	@Override
	public void orderDetail(OrderDetail[] detail, long userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("detail", detail);
//		sql.insert("order.orderDetail", map);
	}
	@Override
	public List<OrderList> orderList(Map<String, Object> map) {
		return sql.selectList("order.orderList", map);
	}
	@Override
	public List<OrderList> orderList(long userId) {
		return sql.selectList("order.orderList", userId);
	}
	@Override
	public OrderList orderListDetail(String orderNum) {
		return sql.selectOne("order.orderListDetail", orderNum);
	}
}
