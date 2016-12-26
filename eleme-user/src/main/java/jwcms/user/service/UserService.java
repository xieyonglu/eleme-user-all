package jwcms.user.service;

import jwcms.user.model.User;

public interface UserService {

	public User createUser(User user) throws Exception;
	
	public boolean removeUser(Long userId) throws Exception;
	
	public User updateUser(User user) throws Exception;
	
	public User queryUserById(Long userId) throws Exception;
	
}
