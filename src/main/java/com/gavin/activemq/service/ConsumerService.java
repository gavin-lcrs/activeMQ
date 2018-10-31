package com.gavin.activemq.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
    @Autowired
    private JmsTemplate jmsTemplate;
    
    //从指定的Destination接收消息
    public TextMessage recive(Destination destination){
        TextMessage message = (TextMessage) jmsTemplate.receive(destination);
        try {
            logger.info("从队列" + destination.toString() + "收到了消息" + message.getText());
        } catch (JMSException e) {
            logger.error("从指定的Destination接收消息异常",e);
        }
        return message;
    }
    //从默认的Destination接收消息
    public void reciveDefault(){
    
        Destination destination = jmsTemplate.getDefaultDestination();
        jmsTemplate.setReceiveTimeout(5000);
        while(true){
            TextMessage message = (TextMessage) jmsTemplate.receive(destination);
            try {
                //这里还是同一个消费者
                logger.info("消费者  从目的地 " + destination.toString() + " 收到了消息" + message.getText());
            } catch (JMSException e) {
                logger.error("从默认的Destination接收消息异常",e);
            }
        }
    }
}