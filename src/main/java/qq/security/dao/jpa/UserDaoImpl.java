package qq.security.dao.jpa;

import org.springframework.stereotype.Repository;

import qq.security.dao.jpa.utils.AbstractBaseDaoImpl;
import qq.security.model.User;

@Repository
public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

}