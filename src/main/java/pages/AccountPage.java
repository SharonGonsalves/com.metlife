package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;
import common.CommonWaits;

public class AccountPage {

	WebDriver driver;
	CommonWaits waits;

	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//select[@id='login-select-tooltip']")
	WebElement accountType;
	@FindBy(xpath = "(//select[@class='login-type'])[2]")
	WebElement accountOption;
	@FindBy(css = ".brand-button.js-submitLogin")
	WebElement logInButton;
	
	
	
	public void accountTypeStep() {
		selectDropdown(accountType, "for_businesses");
		selectDropdown(accountOption, "metlink");
	    click(logInButton);
	}
}
