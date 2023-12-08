package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;

	public LoginPage(WebDriver rDriver) {
		ldriver = rDriver;

		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id = "Email")
	WebElement email;

	@FindBy(id = "Password")
	WebElement password;

	@FindBy(xpath = "//button[@class='button-1 login-button']")
	WebElement LoginBtn;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logout;

	public void enterEmail(String emailAdd) {
		email.clear();
		email.sendKeys(emailAdd);
	}

	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}

	public void clickOnLoginButton() {
		LoginBtn.click();
	}

	public void clickOnLogOutButton() {
		JavascriptExecutor executor = (JavascriptExecutor) ldriver;
		executor.executeScript("arguments[0].click();", logout);
		// logout.click();
	}

}
