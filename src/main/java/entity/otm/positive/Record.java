package entity.otm.positive;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entity.IdEntity;

@Entity
@Table(name = "records")
public class Record extends IdEntity {

	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	

	/*
	 * @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional
	 * = true)
	 * 
	 * @JoinColumn(name = "form_id")
	 */
	
	private Form form;
	@ManyToOne
	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}
