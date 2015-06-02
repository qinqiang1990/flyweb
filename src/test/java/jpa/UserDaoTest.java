package jpa;

import java.util.LinkedHashMap;

import org.junit.*;

import qq.security.dao.*;
import qq.security.dao.base.*;
import qq.security.model.DBUser;

public class UserDaoTest {
	DBUserDao userDao = null;

	@Before
	public void before() {
		userDao = new DBUserDaoImpl();
		JPAUtils.getEntityManager().getTransaction().begin();
	}

	@Test
	public void testPersist() throws Exception {
		DBUser user = new DBUser();
		user.setUsername("ssssss");
		userDao.persist(user);
	}

	@Test
	public void testFind() throws Exception {
		DBUser user = userDao.find(2L);
		Assert.assertNotNull(user);
		Assert.assertEquals("ssssss", user.getUsername());
	}

	@Test
	public void testMerge() throws Exception {
		DBUser user = new DBUser();
		user.setId(2L);
		user.setUsername("aaaa");
		userDao.merge(user);
		user = userDao.find(2L);
		Assert.assertNotNull(user);
		Assert.assertEquals("aaaa", user.getUsername());

	}

	@Test
	public void testRemove() throws Exception {
		DBUser user = null;
		userDao.remove(1L);
		user = userDao.find(1L);
		Assert.assertNull(user);
	}

	@Test
	public void testQueryForProperty() throws Exception {
		Object result = userDao.queryForProperty("username", 2L);
		Assert.assertNotNull(result);
	}

	@Test
	public void testQuery() throws Exception {
		Object[] params = {};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "desc");
		QueryResult<DBUser> qr = userDao.query(0, 2, "", params, orderBy);
		Assert.assertNotNull(qr);
		Assert.assertEquals(2, qr.getCount());
		Assert.assertEquals(3, qr.getResults().get(0).getAccess());
	}

	@After
	public void after() {
		JPAUtils.getEntityManager().getTransaction().commit();
		JPAUtils.freeEntityManager();
		JPAUtils.getEntityManagerFactory().close();
	}
}