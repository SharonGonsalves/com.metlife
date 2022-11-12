package pages;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import base.BaseClass;

public class HomePageTest extends BaseClass {

	@Test(enabled = false, priority = 1)
	public void logInAccountTest(){
		homePage.logInButtonStep();
		accountPage.accountTypeStep();
		logInPage.logInSteps("Sharon","12345");
	}
	
	@Parameters({"username","password"})
	@Test(enabled = true, priority = 2)
	public void logInAccountParameterTest(String username, String password){
		homePage.logInButtonStep();
		accountPage.accountTypeStep();
		logInPage.logInSteps(username,password);
	}

}
