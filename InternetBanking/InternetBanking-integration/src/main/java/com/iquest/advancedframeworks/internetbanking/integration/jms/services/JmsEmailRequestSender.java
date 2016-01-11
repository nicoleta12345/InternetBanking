package com.iquest.advancedframeworks.internetbanking.integration.jms.services;

import java.io.StringWriter;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.iquest.advancedframeworks.internetbanking.integration.jms.model.request.EmailRequest;

/**
 * The JmsEmailRequestSender class is used to send requests to the jms email simulator.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Component
public class JmsEmailRequestSender {

  /**
   * The JmsTemplate object used to send the request.
   */
  @Autowired
  private JmsTemplate jmsTemplate;

  /**
   * Sends a email using a JmsTemplate object. Before is sent, the mail is converted to a xml format.
   * 
   * @param to the receiver of the email
   * @param from the sender of the email
   * @param subject the subject of the email
   * @param body the body of the email
   */
  public void sendMail(String to, String from, String subject, String body) {
    JAXBContext jaxbContext = null;
    Marshaller jaxbMarshaller = null;
    StringWriter sw = new StringWriter();

    EmailRequest email = new EmailRequest();
    email.setEmail(to);
    email.setSenderEmail(from);
    email.setSubject(subject);
    email.setBody(body);

    try {
      jaxbContext = JAXBContext.newInstance(EmailRequest.class);
      jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(email, sw);
    }
    catch (JAXBException e1) {
      // stay silent
    }

    String xmlString = sw.toString();
    String correlationId = getCorrelationId(email);

    sendMessage(xmlString, correlationId);
  }

  /**
   * Computes the correlation Id for an email request.
   * 
   * @param email the email request
   * @return the correlation id
   */
  private String getCorrelationId(EmailRequest email) {
    Random rand = new Random();
    int hashcode = email.hashCode();
    int correlationId = hashcode + rand.nextInt(hashcode);

    return String.valueOf(correlationId);
  }

  /**
   * Sends a request to a jms simulator using a JmsTemplate object.
   * 
   * @param msg the message which will be sent.
   * @param correlationId the correlationId of the request
   */
  public void sendMessage(final String msg, final String correlationId) {
    jmsTemplate.send(new MessageCreator() {

      @Override
      public Message createMessage(Session session) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(msg);
        message.setJMSCorrelationID(correlationId);

        return (Message) message;
      }
    });
  }

}
