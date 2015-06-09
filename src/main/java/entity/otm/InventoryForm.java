package entity.otm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import entity.IdEntity;

/**
 * 单据
 * @author Jack
 */
@SuppressWarnings("all")
@Entity
@Table(name = "kc_forms")
public class InventoryForm extends IdEntity {

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private Set<InventoryRecord> records = new HashSet<InventoryRecord>();

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "form")
	public Set<InventoryRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<InventoryRecord> records) {
		this.records = records;
	}

	public void addRecords(InventoryRecord record) {
		record.setForm(this);// 用关系维护端来维护关系
		this.records.add(record);
	}
}
