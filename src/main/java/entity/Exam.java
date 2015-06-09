package entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table; 

@Entity
@Table(name = "e_exam")
public class Exam extends IdEntity {

  private String e_name;


  private Set<Subject> subjects=new  HashSet<Subject>();

  public String getE_name() {
    return e_name;
  }

  public void setE_name(String e_name) {
    this.e_name = e_name;
  }

  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="e_exam_subject",    
  joinColumns={    
      @JoinColumn(name="e_id",referencedColumnName="id")    
  },    
  inverseJoinColumns={    
      @JoinColumn(name="s_id",referencedColumnName="id")    
  }    
      )   
  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

}
