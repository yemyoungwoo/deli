package ymw.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import ymw.delivery.dto.CartList;
import ymw.delivery.dto.OrderInfo;
import ymw.delivery.dto.OrderList;
import ymw.delivery.login.LoginService;
import ymw.delivery.util.Page;



public interface OrderService {

	//	장바구니에 담긴 금액과 db의 금액이 같은지 확인
	long orderPriceCheck(CartList cartList);
	
	public String order(CartList cart, OrderInfo info, LoginService user);


	public String order(CartList cart, OrderInfo info, LoginService user, HttpSession session);
	
	// 주문목록
	List<OrderList> orderList(long userId);

	// 주문목록 상세보기
	OrderList orderListDetail(String orderNum);
	
	//페이지
	List<OrderList> orderList(long userId, Page p);
}