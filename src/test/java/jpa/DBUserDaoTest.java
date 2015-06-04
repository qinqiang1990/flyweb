package jpa;

import java.util.LinkedHashMap;

import org.junit.*;

import qq.security.dao.*;
import qq.security.dao.base.*;
import qq.security.model.DBUser;

public class DBUserDaoTest {
	DBUserDao dbuserDao = null;

	@Before
	public void before() {
		dbuserDao = new DBUserDaoImpl();
		JPAUtils.getEntityManager().getTransaction().begin();
	}

	@Test
	public void testPersist() throws Exception {
		DBUser user = new DBUser();
		user.setUsername("ssssss");
		dbuserDao.persist(user);
	}

	@Test
	public void testFind() throws Exception {
		DBUser user = dbuserDao.find(1L);
		Assert.assertNotNull(user);
		Assert.assertEquals("ssssss", user.getUsername());
	}

	@Test
	public void testMerge() throws Exception {
		DBUser user = new DBUser();
		user.setId(1L);
		user.setUsername("aaaa");
		dbuserDao.merge(user);
		user = dbuserDao.find(1L);
		Assert.assertNotNull(user);
		Assert.assertEquals("aaaa", user.getUsername());

	}

	@Test
	public void testRemove() throws Exception {
		DBUser user = null;
		dbuserDao.remove(2L);
		user = dbuserDao.find(2L);
		Assert.assertNull(user);
	}

	@Test
	public void testQueryForProperty() throws Exception {
		Object result = dbuserDao.queryForProperty("username", 1L);
		Assert.assertNotNull(result);
	}

	@Test
	public void testQuery() throws Exception {
		Object[] params = {};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "desc");
		QueryResult<DBUser> qr = dbuserDao.query(0, 2, "", params, orderBy);
		Assert.assertNotNull(qr);
		Assert.assertEquals(4, qr.getCount());

	}

	@After
	public void after() {
		JPAUtils.getEntityManager().getTransaction().commit();
		JPAUtils.freeEntityManager();
		JPAUtils.getEntityManagerFactory().close();
	}
}