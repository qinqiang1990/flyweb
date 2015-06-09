package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 

@Entity
@Table(name = "slave")
public class Slave extends IdEntity {

  private String nodedata;

  private String ip;




  private	Master master;

  public void setMaster(Master master) {
    this.master = master;
  }
  @ManyToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
  @JoinColumn(name="mastersss_id")
  public Master getMaster() {
    return master;
  }  

  public String getNodedata() {
    return nodedata;
  }
  public void setNodedata(String nodedata) {
    this.nodedata = nodedata;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  } 



}