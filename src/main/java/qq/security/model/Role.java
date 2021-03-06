package qq.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.BaseModel;

@Entity
@Table(name = "role")
public class Role extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3743915304755056855L;

	private String rolename;

	private String access;

	private Set<User> users = new HashSet<User>();;

	private Set<Controller> controllers = new HashSet<Controller>();;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_controller", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "controller_id", referencedColumnName = "id") })
	public Set<Controller> getControllers() {
		return controllers;
	}

	public void setControllers(Set<Controller> controllers) {
		this.controllers = controllers;
	}

}