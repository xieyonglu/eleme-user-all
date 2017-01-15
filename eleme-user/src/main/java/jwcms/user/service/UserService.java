package jwcms.user.service;

import jwcms.user.model.User;

public interface UserService {

	/**
	 * 创建用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User createUser(User user) throws Exception;
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean removeUser(Long userId) throws Exception;
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User updateUser(User user) throws Exception;
	
	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User queryUserById(Long userId) throws Exception;
	
}
