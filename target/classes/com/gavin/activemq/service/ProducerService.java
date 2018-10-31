package com.gavin.activemq.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {
	Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    //ʹ��Ĭ��Ŀ�ĵ�
    public void sendMessageDefault(final String msg){
    	
        Destination destination = jmsTemplate.getDefaultDestination();

        logger.info("����У� " + destination + " �ɹ�����һ����Ϣ");

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    //��ָ��Ŀ�ĵ�
    public void sendMessage(Destination destination,final String msg){
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}