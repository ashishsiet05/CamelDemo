/**
 * 
 */
package com.camel.demo.route;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rajup
 *
 */
@Configuration
public class JMSConfiguration {

	@Bean(name = "activemq")
	public JmsComponent jmsComponent(ConnectionFactory connectionFactory) {
		return JmsComponent.jmsComponentTransacted(connectionFactory);
	}

}
