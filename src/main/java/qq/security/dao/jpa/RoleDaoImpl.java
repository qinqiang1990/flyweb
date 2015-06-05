package qq.security.dao.jpa;

import org.springframework.stereotype.Repository;

import qq.security.dao.jpa.utils.AbstractBaseDaoImpl;
import qq.security.model.Role;

@Repository
public class RoleDaoImpl extends AbstractBaseDaoImpl<Role> implements RoleDao {
}