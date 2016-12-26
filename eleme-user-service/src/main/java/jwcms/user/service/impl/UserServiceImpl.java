package jwcms.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwcms.user.dao.UserDao;
import jwcms.user.model.User;
import jwcms.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User createUser(User user) throws Exception {
		System.out.println("==Service CreateUser==");
		
		userDao.createUser(null);
		return null;
	}

	@Override
	public boolean removeUser(Long userId) throws Exception {
		System.out.println("==Service RemoveUser==");
		
		userDao.removeUser(userId);
		return false;
	}

	@Override
	public User updateUser(User user) throws Exception {
		System.out.println("==Service UpdateUser==");
		
		userDao.updateUser(null);
		return null;
	}

	@Override
	public User queryUserById(Long userId) throws Exception {
		System.out.println("==Service QueryUserById==");
		
		userDao.queryUserById(userId);
		return null;
	}

}
