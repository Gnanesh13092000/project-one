package com.AccountServiceAPI.AccountServiceAPI.stepdefs;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.AccountServiceAPI.AccountServiceAPI.Entity.Account;
import com.AccountServiceAPI.AccountServiceAPI.Model.AccountMessage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class KafkaStepDefs {

    private final XmlMapper xmlMapper = new XmlMapper();
    private String xmlMessage;
    private AccountMessage parsedMessage;
    private Account responseAccount;
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Given("a valid XML message with accountId {string} and currency {string}")
    public void a_valid_xml_message(String accountId, String currency) throws Exception {
        parsedMessage = new AccountMessage();
        parsedMessage.setAccountId(accountId);
        parsedMessage.setCurrency(currency);

        xmlMessage = xmlMapper.writeValueAsString(parsedMessage);

        System.setOut(new PrintStream(outContent));
    }

    @When("the message is consumed by Kafka listener")
    public void the_message_is_consumed() throws Exception {
        parsedMessage = xmlMapper.readValue(xmlMessage, AccountMessage.class);

        String url = "http://localhost:8080/api/accounts?accountId=" + parsedMessage.getAccountId();
        ResponseEntity<Account> response = restTemplate.getForEntity(url, Account.class);

        responseAccount = response.getBody();

        System.out.println("Account Details: " + responseAccount);
        System.out.println("Currency: " + parsedMessage.getCurrency());
    }

    @Then("the REST API should return the account details for {string}")
    public void the_rest_api_should_return_account_details(String accountId) {
        Assertions.assertNotNull(responseAccount);
        Assertions.assertEquals(accountId, responseAccount.getAccountId());
    }

    @And("the console should print the account details and currency")
    public void console_should_print_details() {
        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Account Details"));
        Assertions.assertTrue(output.contains("Currency: " + parsedMessage.getCurrency()));
    }
}
