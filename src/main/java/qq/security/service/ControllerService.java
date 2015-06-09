package qq.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qq.security.dao.jpa.ControllerDao;
import qq.security.model.Controller;
import qq.security.model.Role;

@Service
public class ControllerService {

	@Autowired
	ControllerDao controllerDao;

	@CacheEvict(value = "ControllerCache", allEntries = true)
	public Controller find(Long entityId) {
		return controllerDao.find(entityId);
	}

	@Transactional
	@Cacheable(value = "ControllerCache")
	// 获取权限列表
	public Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {

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