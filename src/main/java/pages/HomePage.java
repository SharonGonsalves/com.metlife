package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.CommonActions;
import common.CommonWaits;

public class HomePage {

	WebDriver driver;
	CommonWaits waits;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//span[@class='header__login-label font-meta-1'])[1]")
	WebElement logInButton;

    
	public void logInButtonStep() {
    CommonActions.click(logInButton);
	}
	}