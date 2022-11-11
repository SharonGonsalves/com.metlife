package pages;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import base.BaseClass;
import utils.data.AutoData;

public class AutoDataTest extends BaseClass{

	AutoData autoData=new AutoData("sharon", "abc123");

	@Test
	public void autoTest() {
		homePage.logInButtonStep();
		accountPage.accountTypeStep();
		logInPage.logInSteps(autoData.getUserName(), autoData.getPassWord());
	}
}
