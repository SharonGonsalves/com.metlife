package common;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import reporting.Logs;

public class CommonActions {
public static void click (WebElement element) {
try {

element.click();
Logs.log(element + "<--- has been clicked");
} catch (NoSuchElementException | NullPointerException e) {
	e.printStackTrace();
	Logs.log("ELEMENT NOT FOUND------>"+ element);
	Assert.fail();
}	
	}
public static boolean isPresent(WebElement element) {
	try {
		element.isEnabled();
		Logs.log(element + " <--- has been PRESENT");
		return true;
	}catch(NoSuchElementException | NullPointerException e) {
		Logs.log("ELEMENT NOT FOUND -->" + element);
		return false;
	}
}

}
