package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

enum Deleted {
	DEFAULT, SUCCESS, FAILURE
}

@MappedSuperclass
public abstract class BaseModel {

	protected Long id;
	private Date updateTime;
	private Date createTime;
	private Date deletedTime;
	private Deleted deleted = Deleted.DEFAULT;
	private Integer status = Integer.valueOf(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Enumerated(EnumType.STRING)
	public Deleted getDeleted() {
		return deleted;
	}

	public void setDeleted(Deleted deleted) {
		this.deleted = deleted;
	}

	@PrePersist
	public void onCreate() {
		createTime = updateTime = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		updateTime = new Date();
	}
}
