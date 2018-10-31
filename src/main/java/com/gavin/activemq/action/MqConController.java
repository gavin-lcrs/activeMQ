package com.gavin.activemq.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gavin.activemq.service.ConsumerService;

@Controller
public class MqConController {
	Logger logger = LoggerFactory.getLogger(MqConController.class);

    @Autowired
    private ConsumerService consumerService;


	@RequestMapping(value = "/con", method = RequestMethod.GET)
	@ResponseBody
    public void msgCon(HttpServletRequest request, HttpServletResponse response){
		logger.info("������ 1 ��ʼ������Ϣ��");
        consumerService.reciveDefault();
    }
}