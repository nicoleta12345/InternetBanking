package com.iquest.advancedframeworks.internetbanking.integration.jms.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The EmailResponse class represents the model for the response of the request for jms email simulator.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailResponse {

  /**
   * The status of the email. It specifies if it could be sent or not.
   */
  @XmlElement(name = "STATUS", required = true)
  private String status;

  /**
   * The error code. There is a code for success and another for failure.
   */
  @XmlElement(name = "ERROR_CODE", required = true)
  private Integer errorCode;

  /**
   * The error message if the mail could not be sent.
   */
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
    return "EmailResponse [status=" + status + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
  }

}
