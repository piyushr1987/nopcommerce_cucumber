package stepDefinition;

import org.openqa.selenium.WebDriver;

import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public WebDriver driver;
	public LoginPage login;
	public AddNewCustomerPage customer;
	public SearchCustomerPage search;
	public static Logger log;

	// generate unique email id
	public String generateEmailId() {
		return (RandomStringUtils.randomAlphabetic(5));
	}

}
