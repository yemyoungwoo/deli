package ymw.delivery.controller;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ymw.delivery.dto.Join;
import ymw.delivery.dto.Review;
import ymw.delivery.login.LoginService;
import ymw.delivery.service.UserService;
import ymw.delivery.util.UserInfoSessionUpdate;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private BCryptPasswordEncoder encodePwd;
	
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
	
	@GetMapping("/user/myInfo")
	public String myInfo() {
		return "user/myInfo";
	}
	
	// 내 비밀번호, 닉네임 수정하기
	@PatchMapping("/user/info")
	public ResponseEntity<String> modifyInfo(String value, String valueType, String prevPassword, 
	        @AuthenticationPrincipal LoginService user, HttpSession session) {
	    // value = 변경할 값
	    // valueType = password, nickname, phone
	    String username = user.getUser().getUsername();
	    String msg = "";
	    
	    switch (valueType) {
	    case "password":
	        if(!encodePwd.matches(prevPassword, user.getPassword())) {
	            return new ResponseEntity<String>("현재 비밀번호가 일치하지 않습니다", HttpStatus.OK);
	        } 
	        value = encodePwd.encode(value);
	        msg = "비밀번호가 변경되었습니다";
	        break;
	        
	    case "nickname":
	        msg = "닉네임이 변경되었습니다";
	        break;
	    case "phone":
	        msg = "전화번호가 변경되었습니다";
	        session.setMaxInactiveInterval(0);
	        session.setAttribute("authNum", null);
	        break;
	    }
	    
	    userService.modifyInfo(username, valueType, value);
	    UserInfoSessionUpdate.sessionUpdate(value, valueType, user);
	    
	    return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}