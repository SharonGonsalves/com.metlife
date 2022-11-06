package base;

import java.util.concurrent.TimeUnit;
import static utils.IConstant.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AccountPage;
import pages.HomePage;
import pages.LogInPage;
import utils.Configuration;

public class BaseClass {
	Configuration config = new Configuration();
	WebDriver driver;
	protected HomePage homePage;
	protected AccountPage accountPage;
	protected LogInPage logInPage;

	@BeforeMethod
	public void setUpDriver() {
		initDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty(URL));
		long pageLoadTime = Long.parseLong(config.getProperty(PAGELOAD_WAIT));
		long implicitWait = Long.parseLong(config.getProperty(EXPLICIT_WAIT));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().pageLoadTimeout(pageLoadTime,TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		driver.manage().timeouts().implicitlyWait(implicitWait,TimeUnit.SECONDS);
		initClasses();
	}

	private void initDriver() {
		//ChromeOptions options=new ChromeOptions();
		//options.addArguments("--incognito");
		//options.add_experimental_option("excludeSwitches","enable-automation");
		//options.add_experimental_option("useAutomationExtension", false);

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
		accountPage= new AccountPage(driver);
		logInPage= new LogInPage(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void closingDriverSession() {
		getDriver().quit();
	}

}