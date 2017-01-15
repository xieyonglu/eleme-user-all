package jwcms.user.converter;

import org.springframework.stereotype.Service;

import jwcms.common.converter.Converter;
import jwcms.user.dao.model.TUser;
import jwcms.user.model.User;


/**
 * 用户表表tb_user实体封装类属性转换器
 * 
 * @author 谢永路（573546261@qq.com）
 */
@Service
public class UserConverter extends Converter<User, TUser> {
	
	@Override
	protected TUser defaultInvert(User source) throws Exception {
		TUser target = new TUser();
		
		target.setId(source.getId());
		target.setUserName(source.getUserName());
		target.setPassword(source.getPassword());
		
		return target;
	}
	
	@Override
	protected User defaultConvert(TUser source) throws Exception {
		User target = new User();
		
		target.setId(source.getId());
		target.setUserName(source.getUserName());
		target.setPassword(source.getPassword());
		
		return target;
	}
	
}
