package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LoginPage;

public class StepDef {

	public WebDriver driver;
	public LoginPage login;

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		login = new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		login.enterEmail(email);
		login.enterPassword(password);

	}

	@When("Click on Login")
	public void click_on_login() {
		login.clickOnLoginButton();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String actTitle) {
		String expTitle = driver.getTitle();
		if (actTitle.equalsIgnoreCase(expTitle)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		login.clickOnLogOutButton();

	}

	@Then("close browser")
	public void close_browser() {
		driver.close();

	}

}
