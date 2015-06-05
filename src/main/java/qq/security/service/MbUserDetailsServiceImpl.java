package qq.security.service;
 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class MbUserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/*private static Logger log = LoggerFactory
			.getLogger(MyBaitsUserDetailsServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	*//**
	 * @param account 登录帐号
	 *//*
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException {
		log.info("登录账号：" + account);
		org.springframework.security.core.userdetails.User userDetails = null;
		User user = userMapper.selectByAccount(account);

		// 账号密码错误，可以在这里手动抛出异常，让验证失败处理器AuthenticationFailureHandler进行处理

		Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		userDetails = new org.springframework.security.core.userdetails.User(
				user.getAccount(), user.getPassword(), enables,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuthorities);
		return userDetails;
	}

	*//**
	 * 根据用户获取该用户拥有的角色
	 * @param user
	 * @return
	 *//*
	private Set<GrantedAuthority> getGrantedAuthorities(User user) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		List<Role> roles = roleMapper.selectByUserId(user.getId());
		if (roles != null) {
			for (Role role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role
						.getName()));
			}
		}
		return grantedAuthorities;
	}*/

}
