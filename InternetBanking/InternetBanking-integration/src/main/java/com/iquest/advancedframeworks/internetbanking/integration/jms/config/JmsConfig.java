package com.iquest.advancedframeworks.internetbanking.integration.jms.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.iquest.advancedframeworks.internetbanking.integration.jms.services.JmsEmailService;

/**
 * The JmsConfig class configures the communication to a jms server simulator.
 * 
 * @author Nicoleta Barbulescu
 *
 */
@Configuration
@EnableJms
@ComponentScan(basePackages = "com.iquest.advancedframeworks.internetbanking.integration.jms")
@PropertySource("classpath:mailQueue.properties")
public class JmsConfig {

  /**
   * An Environment object used to read values from a properties file.
   */
  @Autowired
  private Environment env;

  /**
   * Listens for messages on a jms queue.
   */
  @Autowired
  private JmsEmailService listener;

  /**
   * JmsTemplate is used to put messages on a jms queue.
   * 
   * @return a new JmsTemplate object configured to send messages to a specific queue
   */
  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setDefaultDestination(new ActiveMQQueue(env.getProperty("requestQueue.name")));
    jmsTemplate.setConnectionFactory(connectionFactory());
    return jmsTemplate;
  }

  /**
   * Factory for ActiveMQConnections.
   * 
   * @return a new ActiveMQConnectionFactory
   */
  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(env.getProperty("listening.connection"));
    return activeMQConnectionFactory;
  }

  /**
   * Configures a SimpleMessageListenerContainer to have a specific class as a listener for a jms queue.
   * 
   * @return a new SimpleMessageListenerContainer
   */
  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

    container.setConnectionFactory(connectionFactory());
    MessageListenerAdapter adapter = new MessageListenerAdapter();
    adapter.setMessageConverter(emailJaxbMarshallingMessageConverter(emailMarshaller()));
    adapter.setDelegate(listener);
    adapter.setDefaultListenerMethod("onMessage");

    container.setMessageListener(adapter);
    container.setDestinationName(env.getProperty("responseQueue.name"));

    container.start();
    return container;
  }

  /**
   * Used by a converter which converts an object to an xml string.
   * 
   * @return a Jaxb2Marshaller configured to have a specific context path
   */
  @Bean
  public Jaxb2Marshaller emailMarshaller() {
    Jaxb2Marshaller marsh = new Jaxb2Marshaller();
    marsh.setContextPath("com.iquest.advancedframeworks.internetbanking.integration.jms.config");
    return marsh;
  }

  /**
   * Used for converting an object to an xml string.
   * 
   * @param emailMarshaller
   * @return a new converter
   */
  @Bean
  public MarshallingMessageConverter emailJaxbMarshallingMessageConverter(Jaxb2Marshaller emailMarshaller) {
    return new MarshallingMessageConverter(emailMarshaller);
  }

}
