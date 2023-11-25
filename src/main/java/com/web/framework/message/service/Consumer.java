package com.web.framework.message.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.web.framework.util.Publisher;
import com.web.framework.vo.EventVo;
import com.web.framework.vo.KafkaVo; 
@Service
public class Consumer {
	
	@Autowired
	Publisher publisher;
	 

	@KafkaListener(topics = KafkaVo.KAFKA_TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<String, String> payload) {
		try {

			publisher.publish(new Gson().fromJson(payload.value(), EventVo.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
