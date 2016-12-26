package jwcms.user.dao;

import jwcms.user.dao.model.TUser;

public interface UserDao {

	public TUser createUser(TUser user) throws Exception;

	public boolean removeUser(Long userId) throws Exception;

	public TUser updateUser(TUser user) throws Exception;

	public TUser queryUserById(Long userId) throws Exception;
}
