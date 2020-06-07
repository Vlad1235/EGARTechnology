package com.egartechnology.fullstack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "securities")
public class SecuritiesEntity implements Serializable {

  private static final long serialVersionUID = -659715927153282325L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false,unique = true)
  private String uuid;
  @Column(nullable = false)
  private LocalDate dateIssue;
  @Column(nullable = false)
  private String securityName;
  private int price;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public LocalDate getDateIssue() {
    return dateIssue;
  }

  public void setDateIssue(LocalDate dateIssue) {
    this.dateIssue = dateIssue;
  }

  public String getSecurityName() {
    return securityName;
  }

  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
