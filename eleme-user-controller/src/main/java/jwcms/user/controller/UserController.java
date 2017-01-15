package jwcms.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jwcms.user.model.User;
import jwcms.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create_user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public void createUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @RequestBody User user) throws Exception {
		System.out.println("==Controller CreateUser==");
		
		userService.createUser(null);
	}

	@RequestMapping(value = "/delete_user/{userId}", method = RequestMethod.DELETE)
	public void removeUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @PathVariable Long userId) throws Exception {
		System.out.println("==Controller RemoveUser==");
		
		userService.removeUser(null);
	}

	@RequestMapping(value = "/update_user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void updateUser(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @RequestBody User user) throws Exception {
		System.out.println("==Controller UpdateUser==");
		
		userService.updateUser(null);
	}

	@RequestMapping(value = "/query_user/{userId}", method = RequestMethod.GET)
	public void queryUserById(HttpServletRequest httpRequest, HttpServletResponse httpRsponse, @PathVariable Long userId) throws Exception {
		System.out.println("==Controller QueryUserById==");
		
		userService.queryUserById(null);
	}
	
}
