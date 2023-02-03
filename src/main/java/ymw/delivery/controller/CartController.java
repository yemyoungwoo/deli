package ymw.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ymw.delivery.dto.Cart;


@Controller
public class CartController {
	
	@ResponseBody
	@PostMapping("/addCart")
	public Cart addCart(Cart cart) {
		
		System.out.println(cart);

		return null;
	}

}