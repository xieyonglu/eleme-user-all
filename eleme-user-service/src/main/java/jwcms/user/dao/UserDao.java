package jwcms.user.dao;

import org.apache.ibatis.annotations.Param;

import jwcms.user.dao.model.TUser;

public interface UserDao {

	/**
	 * 创建用户
	 * @param user
	 * @throws Exception
	 */
	public void createUser(TUser user) throws Exception;

	/**
	 * 删除用户
	 * @param id
	 * @throws Exception
	 */
	public void removeUser(@Param("id") Long id) throws Exception;

	/**
	 * 修改用户
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(TUser user) throws Exception;

	/**
	 * 根据用户ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TUser queryUserById(@Param("id") Long id) throws Exception;
	
}
