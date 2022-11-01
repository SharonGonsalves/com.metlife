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
    @FindBy(xpath="(//div[text()='Auto'])[1]")
    WebElement autoButton;
    @FindBy(xpath = "//span[@class='mr-2']")
    WebElement getaquoteElement;
    
    
	public void autoQuoteSteps() {

		CommonActions.click(quote);
		CommonActions.click(autoButton);
		
	}
}