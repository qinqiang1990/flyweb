package entity.otm.two;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entity.IdEntity;

@Entity
@Table(name = "kc_forms_records")
public class InventoryRecord extends IdEntity {

	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	private InventoryForm form;

	@ManyToOne(cascade = { CascadeType.ALL }, optional = true)
	@JoinColumn(name = "form_id")
	public InventoryForm getForm() {
		return form;
	}

	public void setForm(InventoryForm form) {
		this.form = form;
	}

}
