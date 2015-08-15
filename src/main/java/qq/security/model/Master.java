package qq.security.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import qq.security.model.base.BaseModel;

@Entity
@Table(name = "master")
public class Master extends BaseModel {

	private String nodename;

	private String ip;

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
