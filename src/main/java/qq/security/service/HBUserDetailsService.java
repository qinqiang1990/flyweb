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
public class HBUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger
			.getLogger(HBUserDetailsService.class);

	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		logger.info("loadUserByUsername");

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

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	public List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();
		/* 这里还可以写的更灵活或者更好，暂且这样吧. hardcode的，做灵活也容易，试验局，就这样了。 */
		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		/* 这是授权 */
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
