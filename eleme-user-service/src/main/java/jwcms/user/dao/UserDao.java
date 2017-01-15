package jwcms.user.dao;

import org.apache.ibatis.annotations.Param;

import jwcms.user.dao.model.TUser;

public interface UserDao {

	public TUser createUser(TUser user) throws Exception;

	public boolean removeUser(@Param("id") Long id) throws Exception;

	public TUser updateUser(TUser user) throws Exception;

	public TUser queryUserById(@Param("id") Long id) throws Exception;
	
}
