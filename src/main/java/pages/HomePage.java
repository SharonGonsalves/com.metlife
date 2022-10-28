package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.CommonActions;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "cf128f7c-3b92-47e9-b84c-0e79dd302f4f-desktop-action-button")
	WebElement quote;
    @FindBy(id="lbl--auto")
    WebElement autoElement;
    
	public void getvirtualcare() {

		CommonActions.click(quote);
	}
}