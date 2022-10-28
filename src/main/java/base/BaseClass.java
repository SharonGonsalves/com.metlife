package base;

import java.time.Duration;
import static utils.IConstant.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.Configuration;

public class BaseClass {
	Configuration config = new Configuration();
	WebDriver driver;
	protected HomePage homePage;

	@BeforeMethod
	public void setUpDriver() {
		initDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty(URL));
		long pageLoadTime = Long.parseLong(config.getProperty(PAGELOAD_WAIT));
		long implicitWait = Long.parseLong(config.getProperty(EXPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		initClasses();
	}

	private void initDriver() {
		String browserName = config.getProperty(BROWSER);
		switch (browserName) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}

	public void initClasses() {

		homePage = new HomePage(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void closingDriverSession() {
		getDriver().quit();
	}

}