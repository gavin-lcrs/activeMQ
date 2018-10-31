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
    
    //��ָ����Destination������Ϣ
    public TextMessage recive(Destination destination){
    	
        TextMessage message = (TextMessage) jmsTemplate.receive(destination);
        try {
            logger.info("�Ӷ���" + destination.toString() + "�յ�����Ϣ" + message.getText());
        } catch (JMSException e) {
        	logger.error("��ָ����Destination������Ϣ�쳣",e);
        }
        return message;
    }
    
    //��Ĭ�ϵ�Destination������Ϣ
    public void reciveDefault(){
        Destination destination = jmsTemplate.getDefaultDestination();
        jmsTemplate.setReceiveTimeout(5000);
        while(true){
            TextMessage message = (TextMessage) jmsTemplate.receive(destination);
            try {
                //���ﻹ��ͬһ��������
                logger.info("������  ��Ŀ�ĵ� " + destination.toString() + " �յ�����Ϣ" + message.getText());
            } catch (JMSException e) {
                logger.error("��Ĭ�ϵ�Destination������Ϣ�쳣",e);
            }
        }
    }
}