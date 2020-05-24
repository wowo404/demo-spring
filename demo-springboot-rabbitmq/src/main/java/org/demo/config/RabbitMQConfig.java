package org.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public final static String CLOUD_EXCHANGE = "cloud-exchange";
    public final static String CLOUD_QUEUE = "cloud-queue";
    public final static String CLOUD_ROUTING_KEY = "cloud-routing-key";

//    @Bean
//    public Queue testQueue(){
//        return new  Queue(CLOUD_QUEUE, true);
//    }
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(CLOUD_EXCHANGE);
//    }
//
//    @Bean
//    public Binding bindingExchange(Queue queueMessage, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queueMessage).to(topicExchange).with(CLOUD_ROUTING_KEY);
//    }

}
