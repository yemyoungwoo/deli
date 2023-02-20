package ymw.delivery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ymw.delivery.dto.Cart;
import ymw.delivery.dto.CartList;
import ymw.delivery.dto.OrderInfo;
import ymw.delivery.dto.OrderList;
import ymw.delivery.dto.Review;
import ymw.delivery.login.LoginService;
import ymw.delivery.service.OrderService;
import ymw.delivery.service.UserService;
import ymw.delivery.util.CreateOrderNum;
import ymw.delivery.util.Page;

@Controller
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	@ResponseBody
	@PostMapping("/order/payment-cash")
	public ResponseEntity<String> payment(HttpSession session, OrderInfo orderInfo, long totalPrice, @AuthenticationPrincipal LoginService user) throws IOException {
	    
	    CartList cartList = (CartList) session.getAttribute("cartList");
	    
	    long orderPriceCheck = orderService.orderPriceCheck(cartList);
	    
	    System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck );
	    
	    if(orderPriceCheck == totalPrice) {
	        orderService.order(cartList, orderInfo, user, session);
	        session.removeAttribute("cartList");
	    }

	    return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/order")
	public String order(HttpSession session, Model model, @AuthenticationPrincipal LoginService user) {

		CartList cartList = (CartList) session.getAttribute("cartList");

		model.addAttribute("cartList", cartList);

		System.out.println(user);

		if (user != null) {
			model.addAttribute("user", user.getUser());
		}
		String orderNum = CreateOrderNum.createOrderNum();
		model.addAttribute("orderNum", orderNum);
		return "order/order";
	}
	
	@GetMapping({"/orderList", "/orderList/{page}"})
	public String orderList(@AuthenticationPrincipal LoginService user, Model model, @PathVariable(required = false) Integer page) {
		if (user == null) {
			System.out.println("주문목록 비로그인");
		} else {
			System.out.println("주문목록 로그인");
			long userId = user.getUser().getId();

			Page p = new Page(page);
			List<OrderList> orderList = orderService.orderList(userId, p);
			
//			if (orderList.size() == 0) {
//				return "order/orderList";
//			}

			List<List<Cart>> cartList = new ArrayList<>();

//			for (int i=0;i<orderList.size();i++) {
//				cartList.add(FoodInfoFromJson.foodInfoFromJson(orderList.get(i).getFoodInfo()));
//			}
			p.totalPage(orderList.get(0).getListCount());
			model.addAttribute("page", p);
			model.addAttribute("user", user.getUser());
			model.addAttribute("cartList", cartList);
			model.addAttribute("orderList", orderList);
		}

		return "order/orderList";
	}
	
	@GetMapping("/orderListDetail/{orderNum}")
	public String orderDetail(@PathVariable String orderNum, Model model, @AuthenticationPrincipal LoginService user) {

	    OrderList orderDetail = orderService.orderListDetail(orderNum);
	    
	    if (user != null && (user.getUser().getId() != orderDetail.getUserId())) {
	        System.out.println("다른 사용자");
	        return "redirect:/";
	    } else if (user == null) {
	        System.out.println("비로그인");
	        return "redirect:/";
	    }
	    
//	    List<Cart> list = FoodInfoFromJson.foodInfoFromJson(orderDetail.getFoodInfo());

	    model.addAttribute("orderDetail", orderDetail);
//	    model.addAttribute("cart", list);

	    return "order/orderListDetail";
	}
}