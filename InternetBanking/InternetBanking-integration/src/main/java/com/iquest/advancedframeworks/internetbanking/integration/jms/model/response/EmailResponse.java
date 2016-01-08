package com.iquest.advancedframeworks.internetbanking.integration.jms.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailResponse {

  @XmlElement(name = "STATUS", required = true)
  private String status;
  
  @XmlElement(name = "ERROR_CODE", required = true)
  private Integer errorCode;
  
  @XmlElement(name = "ERROR_MESSAGE", required = true)
  private String errorMessage;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    return "JmsEmailResponse [status=" + status + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
  }

}
