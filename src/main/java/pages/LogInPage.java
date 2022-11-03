package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.CommonActions;

public class LogInPage {

	WebDriver driver;
	CommonActions commonActions;
	
	public LogInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userNameId']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='passwordId']")
	WebElement passWord;
	@FindBy(xpath = "//button[text()=' LOG IN']")
	WebElement logInButton;
	
	public void logInSteps(String username, String password){
		CommonActions.inputText(userName, username);
		CommonActions.inputText(passWord, password);
		CommonActions.click(logInButton);
	}
}
