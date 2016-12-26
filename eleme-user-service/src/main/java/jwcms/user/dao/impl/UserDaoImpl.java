package jwcms.user.dao.impl;

import org.springframework.stereotype.Repository;

import jwcms.user.dao.UserDao;
import jwcms.user.dao.model.TUser;

@Repository
public class UserDaoImpl implements UserDao {

	public TUser createUser(TUser user) throws Exception {
		System.out.println("==Dao CreateUser==");
		return null;
	}

	public boolean removeUser(Long userId) throws Exception {
		System.out.println("==Dao RemoveUser==");
		return false;
	}

	public TUser updateUser(TUser user) throws Exception {
		System.out.println("==Dao UpdateUser==");
		return null;
	}

	public TUser queryUserById(Long userId) throws Exception {
		System.out.println("==Dao QueryUserById==");
		
		return null;
	}

}
