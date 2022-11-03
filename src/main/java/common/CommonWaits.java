package common;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Configuration;
import utils.IConstant;

public class CommonWaits {

	WebDriverWait wait;
	Configuration configuration = new Configuration();
	
	public CommonWaits(WebDriver driver) {
		long waitTime = Long.parseLong(configuration.getProperty(IConstant.IMPLICIT_WAIT));
		//wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait = new WebDriverWait(driver, waitTime);
	}
	
	public void waitUntilVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException | NullPointerException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
