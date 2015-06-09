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
import javax.persistence.OneToOne;
import javax.persistence.Table; 

@Entity
@Table(name = "address")
public class Address extends IdEntity {
 
  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  
  private User user;
  @OneToOne(mappedBy="address")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
 
 
  


}
