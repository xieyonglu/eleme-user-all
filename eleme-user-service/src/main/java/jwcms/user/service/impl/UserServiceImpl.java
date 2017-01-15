package jwcms.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwcms.common.exception.ServiceException;
import jwcms.user.converter.UserConverter;
import jwcms.user.dao.UserDao;
import jwcms.user.dao.model.TUser;
import jwcms.user.exception.UserExceptionCode;
import jwcms.user.model.User;
import jwcms.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserConverter userConverter;

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
		if(userId.compareTo(1L) == 0) {
			throw new ServiceException(UserExceptionCode.USER_NOT_FOUND);
		}
		
		TUser tuser = userDao.queryUserById(userId);
		return userConverter.convert(tuser);
	}

}
