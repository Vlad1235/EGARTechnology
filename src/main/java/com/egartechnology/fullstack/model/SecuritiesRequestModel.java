package com.egartechnology.fullstack.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class SecuritiesRequestModel {


  @NotNull
  private String uuid;
  @NotNull
  private String dateIssue;
  @NotNull
  private String securityName;
  private int price;

  public SecuritiesRequestModel(
      @JsonProperty("uuid") String uuid,
      @JsonProperty("dateIssue") String dateIssue,
      @JsonProperty("securityName") String securityName,
      @JsonProperty("price")int price) {
    this.uuid = uuid;
    this.dateIssue = dateIssue;
    this.securityName = securityName;
    this.price = price;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public LocalDate getDateIssue() throws ParseException {
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date data = simpleDateFormat.parse(dateIssue);
    LocalDate formatedData = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return formatedData;
  }

  public void setDateIssue(String dateIssue) {
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
