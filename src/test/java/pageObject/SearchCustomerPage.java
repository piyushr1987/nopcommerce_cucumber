package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;

	public SearchCustomerPage(WebDriver rDriver) {
		ldriver = rDriver;

		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id = "SearchEmail")
	WebElement txtEmail;

	@FindBy(id = "search-customers")
	WebElement btnSearch;

	@FindBy(xpath = "//div[@class='row']//div[@class='col-md-12']")
	WebElement serachResult;

	@FindBy(xpath = "//div[@class='row']//div[@class='col-md-12']//tbody/tr")
	List<WebElement> tableRows;

	// @FindBy(xpath =
	// "//div[@class='row']//div[@class='col-md-12']//tbody/tr[2]/td")
	// List<WebElement> tableColumns;

	@FindBy(id = "SearchFirstName")
	WebElement txtFirstName;

	@FindBy(id = "SearchLastName")
	WebElement txtLastName;

	public void enterEmail(String emailAdd) {

		txtEmail.sendKeys(emailAdd);
	}

	public void clickOnSearch() {
		btnSearch.click();

	}

	public boolean searchCustomerByEmail(String email) {
		boolean found = false;

		// total no of rows
		int rows = tableRows.size();

		// total no of columns
		// int columns = tableColumns.size();

		for (int i = 1; i <= rows; i++) {
			WebElement webElementEmail = ldriver
					.findElement(By.xpath("//div[@class='row']//div[@class='col-md-12']//tbody/tr[" + i + "]/td[2]"));

			String actEmailAdd = webElementEmail.getText();
			if (actEmailAdd.equalsIgnoreCase(email)) {
				found = true;
			}

		}
		return found;

	}

	public void enterFirstName(String fname) {

		txtFirstName.sendKeys(fname);
	}

	public void enterLastName(String lname) {

		txtLastName.sendKeys(lname);
	}

	public boolean searchCustomerByName(String name) {
		boolean found = false;

		// total no of rows
		int rows = tableRows.size();

		// total no of columns
		// int columns = tableColumns.size();

		for (int i = 1; i <= rows; i++) {
			WebElement webElementName = ldriver
					.findElement(By.xpath("//div[@class='row']//div[@class='col-md-12']//tbody/tr[" + i + "]/td[3]"));

			String actName = webElementName.getText();
			if (actName.equalsIgnoreCase(name)) {
				found = true;
				break;
			}

		}
		return found;

	}
}
