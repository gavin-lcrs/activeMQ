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

import com.gavin.activemq.service.ProducerService;

@Controller
public class MqProController {
	Logger logger = LoggerFactory.getLogger(MqProController.class);

    @Autowired
    private ProducerService producerService;

	@RequestMapping(value = "/pro", method = RequestMethod.GET)
	@ResponseBody
	public void msgPeo(HttpServletRequest request, HttpServletResponse response) {

		int cnt = Integer.parseInt(request.getParameter("cnt")==null ? "0" : request.getParameter("cnt"));
		logger.info("生产者开始发送消息：");

        for(int i = 1; i < cnt; i++){
            String msg = "生产者发出的消息";
            producerService.sendMessageDefault(msg + "-----" + i);
        }
	}
}