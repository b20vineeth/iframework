package com.web.framework.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.web.framework.vo.KafkaVo;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class KafkaProducer {

 
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){ 
        kafkaTemplate.send(KafkaVo.KAFKA_TOPIC_NAME, message);
    }
	 
}