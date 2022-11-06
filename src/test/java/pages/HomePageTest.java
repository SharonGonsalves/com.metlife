package pages;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseClass;

public class HomePageTest extends BaseClass {

	@Test(enabled = false)
	public void logInAccountTest(){
		homePage.logInButtonStep();
		accountPage.accountTypeStep();
		logInPage.logInSteps("Sharon","12345");
		
	}
	@Parameters({"username","password"})
	@Test
	public void logInAccountParameterTest(String username, String password){
		homePage.logInButtonStep();
		accountPage.accountTypeStep();
		logInPage.logInSteps(username,password);
		

	}

}
