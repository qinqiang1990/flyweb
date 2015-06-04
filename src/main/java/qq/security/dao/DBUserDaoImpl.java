package qq.security.dao;

import org.springframework.stereotype.Repository;

import qq.security.dao.base.AbstractBaseDaoImpl;
import qq.security.model.DBUser;

@Repository
public class DBUserDaoImpl extends AbstractBaseDaoImpl<DBUser> implements
		DBUserDao {

}