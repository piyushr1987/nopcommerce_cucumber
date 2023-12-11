package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

public class StepDef extends BaseClass {

	@Before()
	public void setUp() throws IOException {

		readConfig = new ReadConfig();

		// intiailise logger
		log = LogManager.getLogger("StepDef");
		System.out.println("Set up method is executed");

		String browser = readConfig.getBrowser();
		// launch browser
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		login = new LoginPage(driver);

		customer = new AddNewCustomerPage(driver);

		search = new SearchCustomerPage(driver);

		log.info("user launched chrome browser");

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		log.info("url is opened");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		login.enterEmail(email);
		login.enterPassword(password);

		log.info("email and password is entered");

	}

	@When("Click on Login")
	public void click_on_login() {
		login.clickOnLoginButton();

		log.info("login button clicked");

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String actTitle) {
		String expTitle = driver.getTitle();
		if (actTitle.equalsIgnoreCase(expTitle)) {
			log.warn("Test Passed:page title is matched....");
			Assert.assertTrue(true);
		} else {
			log.warn("Test Failed:page title is not matched....");
			Assert.assertTrue(false);
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		login.clickOnLogOutButton();

		log.info("logout button is clicked");

	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
		// driver.quit();

		log.info("browser is closed");

	}

	/////////////////// Add new customer////////////////////////////

	@Then("User can view dashboard")
	public void user_can_view_dashboard() {

		String actTitle = customer.getPageTitle();
		String expTitle = "Dashboard / nopCommerce administration";
		if (actTitle.equalsIgnoreCase(expTitle)) {
			log.warn("Test Passed:user can view dashboard....");
			Assert.assertTrue(true);
		} else {
			log.warn("Test Failed:user is not able to view dashboard....");
			Assert.assertTrue(false);
		}

	}

	@When("user click on customer menu")
	public void user_click_on_customer_menu() {

		customer.clickOnCustomersMenu();

		log.info("customer menu is clicked");

	}

	@When("click on customer menu item")
	public void click_on_customer_menu_item() {

		customer.clickOnCustomersMenuItem();

		log.info("customer menu item is clicked");

	}

	@When("click on add new button")
	public void click_on_add_new_button() {

		customer.clickOnAddnew();

		log.info("add new button is clicked");

	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {

		String actTitle = customer.getPageTitle();
		String expTitle = "Add a new customer / nopCommerce administration";
		if (actTitle.equalsIgnoreCase(expTitle)) {
			log.warn("Test Passed:page title is matched....");
			Assert.assertTrue(true);
		} else {
			log.warn("Test Failed:page title is not matched....");
			Assert.assertTrue(false);
		}

	}

	@When("user enter customer info")
	public void user_enter_customer_info() {

		customer.enterEmail(generateEmailId() + "@gmail.com");
		customer.enterPassword("Demo123@");
		customer.enterFirstName("priyanka");
		customer.enterLastName("sonone");
		customer.enterGender("Female");
		customer.enterDob("11/19/1988");
		customer.enterCompanyName("Facebook");
		customer.enterAdminContent("Admin content");
		customer.enterManagerOfVendor("Vendor 1");

		log.info("customer information is entered");

	}

	@When("click on save button")
	public void click_on_save_button() {

		customer.clickOnSave();

		log.info("save button is clicked");

	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expMessage) {

		String actMessage = driver.findElement(By.tagName("Body")).getText();
		if (actMessage.contains(expMessage)) {
			log.warn("Test Passed:confirmation message is matched....");
			Assert.assertTrue(true);
		} else {
			log.warn("Test Passed:confirmation message is not matched....");
			Assert.assertTrue(false);
		}

	}

	// *************search customer by email*****************//

	@When("enter customer email address")
	public void enter_customer_email_address() {
		search.enterEmail("victoria_victoria@nopCommerce.com");

		log.info("email address is entered");
	}

	@When("click on search button")
	public void click_on_search_button() {
		search.clickOnSearch();

		log.info("search button is clicked");

	}

	@Then("user should found email in search table")
	public void user_should_found_email_in_search_table() {

		String expEmailAdd = "victoria_victoria@nopCommerce.com";
		Assert.assertTrue(search.searchCustomerByEmail(expEmailAdd));

		log.info("email address is found in search table");

	}

	@Then("close the browser.")
	public void close_the_browser() {
		driver.quit();

		log.info("browser is closed");
	}

	///////////////////// search customer by name/////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		search.enterFirstName("Victoria");

		log.info("customer first name is entered");

	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		search.enterLastName("Terces");

		log.info("customer last name is entered");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {

		String expName = "Victoria Terces";
		Assert.assertTrue(search.searchCustomerByName(expName));

		log.info("customer name is found in search table");

	}

	@After()
	public void tearDown(Scenario sc) {
		System.out.println("Tear down method is executed");
		if (sc.isFailed() == true) {
			String fileWithPath = "C:\\Users\\piyush ramteke\\eclipse-workspace\\E workplace\\CucumberFramework\\Screenshots\\failedScreenshot.png";
			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFile = new File(fileWithPath);

			// Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		driver.quit();
	}

	@BeforeStep
	public void beforeTestDemo() {
		System.out.println("This is before test........");
	}

	@AfterStep
	public void afterTestDemo() {
		System.out.println("This is after test........");
	}

}
