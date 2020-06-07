package com.egartechnology.fullstack.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateSecuritiesRequestModel {

  @NotNull
  @NotBlank
  private LocalDate dateIssue;
  @NotNull
  @NotBlank
  @Size(min = 2)
  private String securityName;
  private int price;

  public LocalDate getDateIssue() {
    return dateIssue;
  }

  public CreateSecuritiesRequestModel(
      @JsonProperty("dateIssue") @NotNull LocalDate dateIssue,
      @JsonProperty("securityName") @NotNull String securityName,
      @JsonProperty("price")int price) {
    this.dateIssue = dateIssue;
    this.securityName = securityName;
    this.price = price;
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
