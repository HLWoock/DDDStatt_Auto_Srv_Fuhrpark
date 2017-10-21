package de.woock.ddd.stattauto;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class DddStattAuto_Srv_Fuhrpark {

	public static void main(String[] args) {
		SpringApplication.run(DddStattAuto_Srv_Fuhrpark.class, args);
	}
	
    @Bean
    public ConnectionFactory connectionFactory() {
    	return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }
	
    @Bean
    public JmsTemplate jmsDefektMeldung(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

}
