package entity.otm.positive;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entity.IdEntity;

@Entity
@Table(name = "forms")
public class Form extends IdEntity {

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private Set<Record> records = new HashSet<Record>();

	@OneToMany(cascade = { CascadeType.ALL})
	@JoinColumn(name="form_id")
	public Set<Record> getRecords() {
		return records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

}
