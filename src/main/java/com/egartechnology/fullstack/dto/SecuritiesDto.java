package com.egartechnology.fullstack.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SecuritiesDto implements Serializable {

  private static final long serialVersionUID = 1496924623364639534L;

  private String uuid;
  private LocalDate dateIssue;
  private String securityName;
  private int price;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
