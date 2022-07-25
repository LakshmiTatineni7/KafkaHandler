package com.example.demo.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerBalance;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    
    @Autowired
    private Producer producer;
    
    @Autowired
    ObjectMapper mapper;
    
    private String customer=null;
    private String balance=null;
    
    @KafkaListener(topics = "customer", groupId = "group_id")
    public void customer(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        customer = message;
        if(balance!=null) {
        	publish();
        }
    }
    
    @KafkaListener(topics = "balance", groupId = "group_id")
    public void balance(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        balance = message;
        if(customer!=null) {
        	publish();
        }
    }
    
    public void publish() throws IOException {
    	Map<String,Object> custObj = mapper.readValue(customer, Map.class);
    	Map<String,Object> balObj = mapper.readValue(balance, Map.class);
    	CustomerBalance bal = new CustomerBalance();
    	bal.setAccountId(String.valueOf(balObj.get("accountId")));
    	bal.setBalance((double) balObj.get("balance"));
    	bal.setCustomerId(String.valueOf(balObj.get("customerId")));
    	bal.setPhone(String.valueOf(balObj.get("phone")));
    	producer.sendMessage(bal);
    	customer = null;
    	balance = null;
    	
    }
}