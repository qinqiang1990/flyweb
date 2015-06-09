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
import javax.persistence.OneToMany;
import javax.persistence.Table; 

@Entity
@Table(name = "master")
public class Master extends IdEntity {
 
  private String nodename;

  private String ip;

/*  private Set<Slave> slaves=new HashSet<Slave>();
   
  public void setSlaves(Set<Slave> slaves) {
    this.slaves = slaves;
  }
  @OneToMany(cascade = CascadeType.ALL  )
@JoinColumn(name="mastersss_id")
  public Set<Slave> getSlaves() {
    return slaves;
  }*/
 
  
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
