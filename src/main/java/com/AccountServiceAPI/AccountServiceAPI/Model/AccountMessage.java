package com.AccountServiceAPI.AccountServiceAPI.Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "AccountMessage")
public class AccountMessage {

	@JacksonXmlProperty(localName = "accountId")
    private String accountId;

    @JacksonXmlProperty(localName = "currency")
    private String currency;
    
    
	public AccountMessage() {}
	
	
	public AccountMessage(String accountId, String currency) {
		super();
		this.accountId = accountId;
		this.currency = currency;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
    
}
