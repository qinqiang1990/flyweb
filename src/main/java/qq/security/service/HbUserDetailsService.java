package qq.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qq.security.dao.base.QueryResult;
import qq.security.dao.jpa.DBUserDao;
import qq.security.dao.jpa.UserDao;
import qq.security.model.DBUser;
import qq.security.model.Role;

@Service
public class HbUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger
			.getLogger(HbUserDetailsService.class);

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		logger.info("HbUserDetailsService_loadUserByUsername");

		/* 这里是认证 */
		// DBUser domainUser = userDao.getDatabase(login);
		QueryResult<qq.security.model.User> query = userDao.query(" login=?",
				new Object[] { login });
		qq.security.model.User domainUser = query.getResults().get(0);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(domainUser.getLogin(), domainUser.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, getAuthorities(domainUser.getRoles()));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> set) {
		/* 这是授权 */
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : set) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		return authorities;
	}

}
