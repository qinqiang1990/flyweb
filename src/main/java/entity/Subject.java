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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;  

@Entity
@Table(name = "e_subject")
public class Subject extends IdEntity{
  
  private String s_name; 

 
  private Set<Exam> exams=new HashSet<Exam>();

  public String getS_name() {
    return s_name;
  }

  public void setS_name(String s_name) {
    this.s_name = s_name;
  }

  @ManyToMany(mappedBy="subjects")
  public Set<Exam> getExams() {
    return exams;
  }

  public void setExams(Set<Exam> exams) {
    this.exams = exams;
  }

}
