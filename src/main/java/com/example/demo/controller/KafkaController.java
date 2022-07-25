package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerBalance;
import com.example.demo.service.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody Map<String,Object> map) {
        String message  = String.valueOf(map.get("message"));
        String topic  = String.valueOf(map.get("topic"));
    	this.producer.sendMessage(message,topic);
    }
    
    @PostMapping(value = "/consumeAndpublish")
    public void consumeAndpublish() throws JsonProcessingException {
    	
    	CustomerBalance cbalance = new CustomerBalance();
        this.producer.sendMessage(cbalance);
    }
    
}