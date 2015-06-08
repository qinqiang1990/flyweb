package qq.security.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import model.BaseModel;

@Entity
@Table(name = "dbuser")

public class DBUser  extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5263709290889832177L;
	private String username;
	private String password;
	private Integer access;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

}