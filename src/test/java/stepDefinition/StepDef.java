package stepDefinition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;

public class StepDef {

	public WebDriver driver;
	public LoginPage login;
	public AddNewCustomerPage customer;
	public SearchCustomerPage search;

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		login = new LoginPage(driver);

		customer = new AddNewCustomerPage(driver);

		search = new SearchCustomerPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

	/////////////////// Add new customer////////////////////////////

	@Then("User can view dashboard")
	public void user_can_view_dashboard() {

		String actTitle = customer.getPageTitle();
		String expTitle = "Dashboard / nopCommerce administration";
		if (actTitle.equalsIgnoreCase(expTitle)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@When("user click on customer menu")
	public void user_click_on_customer_menu() {

		customer.clickOnCustomersMenu();

	}

	@When("click on customer menu item")
	public void click_on_customer_menu_item() {

		customer.clickOnCustomersMenuItem();

	}

	@When("click on add new button")
	public void click_on_add_new_button() {

		customer.clickOnAddnew();

	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {

		String actTitle = customer.getPageTitle();
		String expTitle = "Add a new customer / nopCommerce administration";
		if (actTitle.equalsIgnoreCase(expTitle)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@When("user enter customer info")
	public void user_enter_customer_info() {

		customer.enterEmail("test912@gmail.com");
		customer.enterPassword("Demo123@");
		customer.enterFirstName("priyanka");
		customer.enterLastName("sonone");
		customer.enterGender("Female");
		customer.enterDob("11/19/1988");
		customer.enterCompanyName("Facebook");
		customer.enterAdminContent("Admin content");
		customer.enterManagerOfVendor("Vendor 1");

	}

	@When("click on save button")
	public void click_on_save_button() {

		customer.clickOnSave();

	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expMessage) {

		String actMessage = driver.findElement(By.tagName("Body")).getText();
		if (actMessage.contains(expMessage)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	// *************search customer by email*****************//

	@When("enter customer email address")
	public void enter_customer_email_address() {
		search.enterEmail("victoria_victoria@nopCommerce.com");
	}

	@When("click on search button")
	public void click_on_search_button() {
		search.clickOnSearch();

	}

	@Then("user should found email in search table")
	public void user_should_found_email_in_search_table() {

		String expEmailAdd = "victoria_victoria@nopCommerce.com";
		Assert.assertTrue(search.searchCustomerByEmail(expEmailAdd));

	}

	@Then("close the browser.")
	public void close_the_browser() {
		driver.quit();
	}

	///////////////////// search customer by name/////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		search.enterFirstName("Victoria");

	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		search.enterLastName("Terces");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {

		String expName = "Victoria Terces";
		Assert.assertTrue(search.searchCustomerByName(expName));

	}

}
