package qq.security.aop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

import qq.security.dao.jpa.ControllerDao;
import qq.security.model.Controller;
import qq.security.model.Role;

/* 
 *  
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配， 
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。 
 *  
 * 此类在初始化时，应该取到所有资源及其对应角色的定义 
 *  
 * 说明：对于方法的spring注入，只能在方法和成员变量里注入， 
 * 如果一个类要进行实例化的时候，不能注入对象和操作对象， 
 * 所以在构造函数里不能进行操作注入的数据。 
 */

//extends AbstractMethodSecurityMetadataSource implements InitializingBean
@Service
public class MethodSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static final Logger logger = Logger
			.getLogger(MethodSecurityMetadataSource.class);

	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections
			.emptyList();
	// 权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	@Autowired
	private ControllerDao controllerDao;

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		HttpServletRequest request = ((FilterInvocation) object).getRequest();

		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			if (entry.getKey().matches(request)) {
				attrs = entry.getValue();
				break;
			}
		}
		logger.info("URL资源：" + request.getRequestURI() + " -> " + attrs);
		return attrs;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub

		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;

	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@PostConstruct
	public void init() throws Exception {
		this.requestMap = this.bindRequestMap();
		logger.info("init" + this.requestMap);
	}

	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {

		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

		List<Controller> controller_list = controllerDao.query(null, null)
				.getResults();

		Iterator<Controller> controller_it = controller_list.iterator();
		while (controller_it.hasNext()) {
			Controller controller = controller_it.next();
			String url = controller.getUrl();

			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();

			Set<Role> roles = controller.getRoles();
			Iterator<Role> role_it = roles.iterator();
			while (role_it.hasNext()) {
				Role role = role_it.next();
				ConfigAttribute ca = new SecurityConfig(role.getRolename());
				atts.add(ca);
			}
			map.put(new AntPathRequestMatcher(url), atts);
		}

		return map;
	}

}