package demo.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Topic;

/**
 * config of ActiveMQ
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 03:30:10
 */
@Configuration
public class ActiveMqConfig {

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        factory.setRecoveryInterval(1000L);
        return factory;
    }

    @Bean("topicTemplate")
    public JmsTemplate topicJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(true);
        return template;
    }

    @Bean("topicListener")
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-3");
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean("topicA")
    public Topic buildTopicA() {
        return new ActiveMQTopic("topic-a");
    }

    @Bean("topicB")
    public Topic buildTopicB() {
        return new ActiveMQTopic("topic-b");
    }
}
