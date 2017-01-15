package jwcms.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jwcms.user.framework.ResponseEntity;
import jwcms.user.interceptor.LoginRequired;
import jwcms.user.model.User;
import jwcms.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create_user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@LoginRequired
	public ResponseEntity<?> createUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @RequestBody User user) throws Exception {
		System.out.println("==Controller CreateUser==");
		userService.createUser(user);
		
		return ResponseEntity.success();
	}
	

	@RequestMapping(value = "/delete_user/{userId}", method = RequestMethod.DELETE)
	@ResponseBody
	@LoginRequired
	public ResponseEntity<?> removeUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @PathVariable Long userId) throws Exception {
		System.out.println("==Controller RemoveUser==");
		userService.removeUser(userId);
		
		return ResponseEntity.success();
	}
	

	@RequestMapping(value = "/update_user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@LoginRequired
	public ResponseEntity<?> updateUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @RequestBody User user) throws Exception {
		System.out.println("==Controller UpdateUser==");
		userService.updateUser(user);
		
		return ResponseEntity.success();
	}
	

	@RequestMapping(value = "/query_user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	@LoginRequired
	public ResponseEntity<?> queryUserById(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @PathVariable Long userId) throws Exception {
		System.out.println("==Controller QueryUserById==");
		
		return ResponseEntity.success(userService.queryUserById(userId));
	}
	
}
