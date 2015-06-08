package jpa;

import java.util.Set;

import org.junit.*;

import qq.security.dao.jpa.ControllerDao;
import qq.security.dao.jpa.ControllerDaoImpl;
import qq.security.dao.jpa.RoleDao;
import qq.security.dao.jpa.RoleDaoImpl;
import qq.security.dao.jpa.UserDao;
import qq.security.dao.jpa.UserDaoImpl;
import qq.security.model.Controller;
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
		// JPAUtils.getEntityManager().getTransaction().begin();
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
				new Object[] { "ROLE_UPD" });
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

	@After
	public void after() {
		/*
		 * JPAUtils.getEntityManager().getTransaction().commit();
		 * JPAUtils.freeEntityManager();
		 * JPAUtils.getEntityManagerFactory().close();
		 */
	}
}