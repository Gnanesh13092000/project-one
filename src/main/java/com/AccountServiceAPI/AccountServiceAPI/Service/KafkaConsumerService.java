package com.AccountServiceAPI.AccountServiceAPI.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.AccountServiceAPI.AccountServiceAPI.Entity.Account;
import com.AccountServiceAPI.AccountServiceAPI.Model.AccountMessage;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class KafkaConsumerService {

	private final RestTemplate restTemplate;
    private final XmlMapper xmlMapper;

    public KafkaConsumerService() {
        this.restTemplate = new RestTemplate();
        this.xmlMapper = new XmlMapper();
    }

    @KafkaListener(topics = "account-topic", groupId = "account-group")
    public void consume(String message) {
        try {
            AccountMessage accountMessage = xmlMapper.readValue(message, AccountMessage.class);

            String url = "http://localhost:8080/api/accounts/" + accountMessage.getAccountId();
            Account account = restTemplate.getForObject(url, Account.class);

            System.out.println("Account Details: " + account);
            System.out.println("Currency: " + accountMessage.getCurrency());
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
