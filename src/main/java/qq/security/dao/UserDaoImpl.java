package qq.security.dao;

import org.springframework.stereotype.Repository;

import qq.security.dao.base.AbstractBaseDaoImpl;
import qq.security.model.User;

@Repository
public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {
}