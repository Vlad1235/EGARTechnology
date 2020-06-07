package com.egartechnology.fullstack.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SecuritiesResponseModel {

  private String uuid;
  private LocalDate dateIssue;
  private String securityName;
  private int price;

  public SecuritiesResponseModel() {
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getDateIssue() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String text = dtf.format(dateIssue);
    return text;
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
