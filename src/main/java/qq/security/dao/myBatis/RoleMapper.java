package qq.security.dao.myBatis;

import java.util.List;

import qq.security.model.Role;
 public interface RoleMapper{
	
	/**
	 * 根据用户id获取角色集合
	 * @param userId
	 * @return
	 */
	List<Role> selectByUserId(Integer userId);
}
