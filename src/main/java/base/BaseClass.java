package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static utils.IConstant.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AccountPage;
import pages.HomePage;
import pages.LogInPage;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.Logs;
import utils.Configuration;

public class BaseClass {
	Configuration config = new Configuration();
	WebDriver driver;
	ExtentReports extent;
	protected HomePage homePage;
	protected AccountPage accountPage;
	protected LogInPage logInPage;
	
	@BeforeSuite
	public void initiatinExtentReport() {
		extent = ExtentManager.getInstance();
	}

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
	
	@BeforeMethod
	public void beforeEachTest(Method method) {
		String className = method.getDeclaringClass().getSimpleName();
		ExtentTestManager.startTest(method.getName());
		ExtentTestManager.getTest().assignCategory(className);
	}
	
	@AfterMethod
	public void afterEachTest(ITestResult result) {
		for(String testName : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(testName);
		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
			ExtentTestManager.getTest().addScreenCaptureFromPath(takeScreenShot(result.getName()));
		}else {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
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
			break;
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

		@AfterSuite
		public void closeReport() {
			extent.flush();
		}
		
		public String takeScreenShot(String testName) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("_MMddyyyy_hhmmss");
			File localFile = new File("test-output/screenShots/" + testName + format.format(date) +".png");
			TakesScreenshot ss = (TakesScreenshot) driver;
			File driverSS = ss.getScreenshotAs(OutputType.FILE);
			try {
				Files.copy(driverSS, localFile);
				Logs.log("Screen Shot captured at \n" + localFile.getAbsolutePath());
			}catch (IOException e) {
				Logs.log("Error occurs during taking ScreenShot..!");
			}
			return localFile.getAbsolutePath();
		}

}