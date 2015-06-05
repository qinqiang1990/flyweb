package jpa;

import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.*;

import qq.security.dao.*;
import qq.security.dao.base.*;
import qq.security.dao.jpa.ControllerDao;
import qq.security.dao.jpa.ControllerDaoImpl;
import qq.security.dao.jpa.RoleDao;
import qq.security.dao.jpa.RoleDaoImpl;
import qq.security.dao.jpa.UserDao;
import qq.security.dao.jpa.UserDaoImpl;
import qq.security.dao.jpa.utils.JPAUtils;
import qq.security.model.Controller;
import qq.security.model.DBUser;
import qq.security.model.Role;
import qq.security.model.User;

public class UserDaoTest {
	UserDao userDao = null;
	RoleDao roleDao = null;
	ControllerDao controllerDao = null;

	@Before
	public void before() {
		userDao = new UserDaoImpl();
		roleDao = new RoleDaoImpl();
		controllerDao = new ControllerDaoImpl();
		JPAUtils.getEntityManager().getTransaction().begin();
	}

	@Test
	public void testPersist() throws Exception {
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("21232f297a57a5a743894a0e4a801fc3");

		User user = new User();
		user.setLogin("user");
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");

		Role role_admin = new Role();
		role_admin.setRolename("ROLE_ADMIN");

		Role role_user = new Role();
		role_user.setRolename("ROLE_USER");

		user.getRoles().add(role_user);
		admin.getRoles().add(role_user);
		admin.getRoles().add(role_admin);

		userDao.persist(user);
		userDao.persist(admin);
	}

	@Test
	public void testFind() throws Exception {

		Role role = (Role) roleDao.queryBy("rolename=?1",
				new Object[] { "ROLE_UPDATE" });
		Controller controller = new Controller();
		controller.setUrl("/book/update");
		role.getControllers().add(controller);
		roleDao.persist(role);
	}

	@Test
	public void findRole() throws Exception {

		Role role = (Role) roleDao.queryBy("rolename=?1",
				new Object[] { "ROLE_ADD" });

		Set<Controller> controllers = (Set<Controller>) role.getControllers();
		Assert.assertNotNull(role);

		Assert.assertNotEquals(controllers.size(), 0);
	}

	@Test
	public void findController() throws Exception {

		Controller controller = (Controller) controllerDao.queryBy("url=?1",
				new Object[] { "/book/test" });
		Set<Role> roles = (Set<Role>) controller.getRoles();
		Assert.assertNotNull(controller);

		Assert.assertNotEquals(roles.size(), 0);
	}

	/*
	 * @Test public void testFind() throws Exception { DBUser user =
	 * dbuserDao.find(1L); Assert.assertNotNull(user);
	 * Assert.assertEquals("ssssss", user.getUsername()); }
	 * 
	 * @Test public void testMerge() throws Exception { DBUser user = new
	 * DBUser(); user.setId(1L); user.setUsername("aaaa");
	 * dbuserDao.merge(user); user = dbuserDao.find(1L);
	 * Assert.assertNotNull(user); Assert.assertEquals("aaaa",
	 * user.getUsername());
	 * 
	 * }
	 * 
	 * @Test public void testRemove() throws Exception { DBUser user = null;
	 * dbuserDao.remove(2L); user = dbuserDao.find(2L); Assert.assertNull(user);
	 * }
	 * 
	 * @Test public void testQueryForProperty() throws Exception { Object result
	 * = dbuserDao.queryForProperty("username", 1L);
	 * Assert.assertNotNull(result); }
	 * 
	 * @Test public void testQuery() throws Exception { Object[] params = {};
	 * LinkedHashMap<String, String> orderBy = new LinkedHashMap<String,
	 * String>(); orderBy.put("id", "desc"); QueryResult<DBUser> qr =
	 * dbuserDao.query(0, 2, "", params, orderBy); Assert.assertNotNull(qr);
	 * Assert.assertEquals(4, qr.getCount());
	 * 
	 * }
	 */

	@After
	public void after() {
		JPAUtils.getEntityManager().getTransaction().commit();
		JPAUtils.freeEntityManager();
		JPAUtils.getEntityManagerFactory().close();
	}
}