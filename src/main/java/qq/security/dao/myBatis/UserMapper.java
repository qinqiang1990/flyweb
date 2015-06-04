package qq.security.dao.myBatis;

import qq.security.model.User;

public interface UserMapper {

	/**
	 * 根据帐号查询用户
	 * @param account 帐号
	 * @return
	 */
	User selectByAccount(String account);
}
