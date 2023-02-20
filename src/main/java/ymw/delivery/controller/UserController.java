package ymw.delivery.controller;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;
import ymw.delivery.login.LoginService;
import ymw.delivery.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	@GetMapping("/mypage")
	public String myPage() {
		return "user/mypage";
	}


	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		String referer = (String) request.getHeader("referer");
		session.setAttribute("referer", referer);
		return "user/login";
	}

	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	
	@PostMapping("/join")
	public String joinProc(@Valid Join join, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<FieldError> list = bindingResult.getFieldErrors();
			Map<String, String> errorMsg = new HashMap<>();
			for(int i=0;i<list.size();i++) {
				String field = list.get(i).getField(); 
				String message = list.get(i).getDefaultMessage(); 
				errorMsg.put(field, message);
			}
			model.addAttribute("errorMsg", errorMsg);
			return "user/join";
		}
		
		String encPwd = pwdEncoder.encode(join.getPassword());
		join.setPassword(encPwd);
		userService.join(join);
		
		return "redirect:/login";
	}
	
	
	@ResponseBody
	@GetMapping("/overlapCheck")
	public int overlapCheck(String value, String valueType) {
//		value = 중복체크할 값
//		valeuType = username, nickname
		System.out.println(value);
		System.out.println(valueType);
		int count = userService.overlapCheck(value, valueType);
		
		System.out.println(count);
		return count;
	}
	
//	@GetMapping("/user/point")
//	public String point(@AuthenticationPrincipal LoginService user, Model model) {
//		long id = user.getUser().getId();
//		List<Point> myPoint = userService.myPoint(id);
//		model.addAttribute("myPoint", myPoint);
//		model.addAttribute("point", user.getUser().getPoint());
//		
//		return "user/point";
//	}
	@GetMapping("/user/myReview")
	public String myreView(@AuthenticationPrincipal LoginService user, Model model) {
		long id = user.getUser().getId();
		List<Review> myReviewList = userService.myReviewList(id);
		model.addAttribute("myReviewList", myReviewList);

		return "user/myReview";
	}



	@DeleteMapping("/user/review")
	public ResponseEntity<String> deleteReview(@AuthenticationPrincipal LoginService user, String orderNum) {
		long id = user.getUser().getId();
		userService.deleteReview(id, orderNum);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/user/point")
	public String point() {
		return "user/point";
	}
}