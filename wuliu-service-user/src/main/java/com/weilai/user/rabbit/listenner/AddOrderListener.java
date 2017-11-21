package com.weilai.user.rabbit.listenner;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import com.weilai.common.util.JsonUtil;
import com.weilai.mq.rabbit.receive.MQReceiveHandler;
import com.weilai.mq.util.RabbitQueue;

/**
 * *********************************
 * 
 * @Description: 
 * @author: liyongzhen
 * @createdAt: 2016年6月2日下午4:14:39
 **********************************
 */
@Service("addOrderListener")
public class AddOrderListener extends MQReceiveHandler {
	@Override
	protected void doBusi(Message message) {
		String queueName = message.getMessageProperties().getConsumerQueue();
		if (RabbitQueue.LIGE_RABBITMQ.toString().equals(queueName)) {
			System.out.println(message.getBody().toString());
			
		}
	}

}
