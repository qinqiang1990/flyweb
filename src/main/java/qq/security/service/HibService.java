package qq.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qq.security.dao.hib.MasterDao;
import qq.security.dao.hib.SubjectDao;
import qq.security.dao.jpa.ControllerDao;
import qq.security.model.Controller;
import qq.security.model.Master;
import qq.security.model.Role;
import qq.security.model.Subject;

@Service
public class HibService {

	private static final Logger log = Logger.getLogger(HibService.class);

	@Autowired
	SubjectDao sdao;

	@Autowired
	MasterDao mdao;

	@Transactional
	public void save(Master master, Subject subject) {
		mdao.save(master);
		sdao.save(subject);

	}

	public void save2(Master master, Subject subject) {
		mdao.save2(master);
		sdao.save2(subject);

	}

}