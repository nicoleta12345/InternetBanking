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

@Configuration
@EnableJms
@ComponentScan(basePackages = "com.iquest.advancedframeworks.internetbanking.integration.jms")
@PropertySource("classpath:mailQueue.properties")
public class JmsConfig {

  @Autowired
  private Environment env;

  @Autowired
  private JmsEmailService listener;

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setDefaultDestination(new ActiveMQQueue(env.getProperty("requestQueue.name")));
    jmsTemplate.setConnectionFactory(connectionFactory());
    return jmsTemplate;
  }

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(env.getProperty("listening.connection"));
    return activeMQConnectionFactory;
  }

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

  @Bean
  public MarshallingMessageConverter emailJaxbMarshallingMessageConverter(Jaxb2Marshaller emailMarshaller) {
    return new MarshallingMessageConverter(emailMarshaller);
  }

  @Bean
  public Jaxb2Marshaller emailMarshaller() {
    Jaxb2Marshaller marsh = new Jaxb2Marshaller();
    marsh.setContextPath("com.iquest.advancedframeworks.internetbanking.integration.jms.config");
    return marsh;
  }

}
