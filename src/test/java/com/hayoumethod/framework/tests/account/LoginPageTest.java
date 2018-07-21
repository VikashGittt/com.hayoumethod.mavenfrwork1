package com.hayoumethod.framework.tests.account;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.pageobject.account.ForgotPasswordPage;
import com.hayoumethod.framework.pageobject.account.LoginPage;
import com.hayoumethod.framework.pageobject.account.RegistrationPage;
import com.hayoumethod.framework.pageobject.homepage.Homepage;
import com.hayoumethod.framework.pageobject.myaccount.AccountDashboard;

public class LoginPageTest extends UI_GenericMethods1 {
	
	protected UI_GenericMethods1 gmObj=new UI_GenericMethods1();
	
	//WebDriver driverObj;
	Homepage homeObj;
	LoginPage lognObj; 
	AccountDashboard acntDbrd;


    @BeforeMethod
    @Parameters({ "Browser", "URL" })
    public void setup(String Browser, String URL) throws IOException{
    	System.out.println("Launch "+URL+" on "+Browser);
       	try {
			homeObj = gmObj.gm_OpenApp(Browser, URL);
			System.out.println("*******Now navigate to login page*******");
			lognObj = homeObj.navigateToLoginPage();
			System.out.println("*******Login page is in display*******");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
   @AfterMethod
   public void setup() throws IOException{	   
	   	System.out.println("closing now");
	   	driverObj.quit();
    }
   
 		/////////////////////////* TC01: Validate Login Process with Valid values
  	@Test (priority = 1)
    public void validateLoginProcess() throws Exception {
			System.out.println("in validateLoginProcess method");
			acntDbrd =  lognObj.validateLogin("1");
			System.out.println("login done using data from test data sheet");
			String actualTitle = AccountDashboard.PageTitle.getText();
			System.out.println("actualTitle = "+actualTitle);
	    	String expectedTitle = "My Dashboard";
	    	System.out.println("Login process validation done");
	    	String elementName = "DashboardPageTitle";
	    	
	    	try {
	    	Assert.assertEquals(actualTitle, expectedTitle);
			
		//	acntDbrd.navigateToAccountInformationPage();
		//	System.out.println(11);
		//	Thread.sleep(20000);
		//	acntDbrd.navigateToAddrsBookPage();
		//	System.out.println(22);
		//	Thread.sleep(20000);
		//	acntDbrd.navigateToNewsLetterSubscriptionPage();
		//	System.out.println(33);
		//	Thread.sleep(20000);
		//	acntDbrd.navigateToMyOrderPage();
		//	System.out.println(44);
		//	Thread.sleep(20000);
		//	acntDbrd.navigateToStoredPaymentMthdPage();
		//	System.out.println(55);
			Thread.sleep(10000);


		} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
		} finally{
			System.out.println("in final method");
			System.out.println(actualTitle);
			//gm_generateReportWithScreenshots_mvn(actualTitle, expectedTitle, elementName);
		}
		System.out.println("Out  of validateLoginProcess method");
	}
 
 
											//	*///
 									/*//TC02: Validate  Login Page Title
    
 	@Test (priority = 2)							
	public void validateLoginPageTitle() throws IOException, InvalidFormatException, InterruptedException{
		System.out.println("in validateLoginPageTitle method");
		lognObj.gm_WaitVIsibility(LoginPage.getPageTitle_txt(), 30);
		WebElement we = LoginPage.getPageTitle_txt();
 		String actualTitle = we.getText();
 		String expectedTitle = "Customer Login";
 		String elementName = "Login Page Title";
 		try{
 			Assert.assertEquals(actualTitle, expectedTitle);	
 		}
 		catch (Exception e) {
 			e.printStackTrace();
 		} finally{
			System.out.println("in final method");
			System.out.println(actualTitle);
			gm_generateReportWithScreenshots_mvn(actualTitle, expectedTitle, elementName);
 	}
 		System.out.println("Out  of validateLoginProcess method");
}
    																	//*/
	
	////////////////////////////////////* //TC03: Validation of Registration Link
	 									//Method1 - via page title
 /* 	@Test(priority = 3)
	public void validateRegistrationLink1() throws Exception{
		 System.out.println("In validateRegistrationLink method");	
	     gm_WaitVIsibility(LoginPage.getRegistration_Lnk(), 30);
	     RegistrationPage regPageObj = lognObj.validateCreateAccntLnk();
		 String regPageActualTitle = regPageObj.getRegistrationPageTitle();
		 String regPageExpectedTitle = "Create New Customer Account";
		 String elementName = "Registration Page Title";
		 try{
			 	Assert.assertEquals(regPageActualTitle, regPageExpectedTitle);
		 }
		 catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally{
				System.out.println("in final method");
				System.out.println(regPageActualTitle);
				gm_generateReportWithScreenshots_mvn(regPageActualTitle, regPageExpectedTitle, elementName);
			}
		 
		System.out.println("Out validateRegistrationLink method");
	}  	 
  		
 /* 	@Test(priority = 4)							//Method2 - via url
	public void validateRegistrationLink2() throws Exception{
			System.out.println("In validateRegistrationLink method");	
			//Thread.sleep(10000);
			gm_WaitVIsibility(lognObj.getRegistration_Lnk(), 30);
			if(lognObj.getRegistration_Lnk().isDisplayed() && lognObj.getRegistration_Lnk().isEnabled()){
				
				String regPageActualURL = homeObj.validateLinks(lognObj.getRegistration_Lnk());
				String regPageExpectedTURL = "https://www.hayoumethod.com/customer/account/create/";
				String elementName = "Registration Page Title";
				try{
					Assert.assertEquals(regPageActualURL, regPageExpectedTURL);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} finally{
					System.out.println("in final method");
					System.out.println(regPageActualURL);
					gm_generateReportWithScreenshots_mvn(regPageActualURL, regPageExpectedTURL, "URL");
				}
			}else{
				System.out.println("Registration link is not working");
			}
			System.out.println("Out validateRegistrationLink method");
	}	 
////////////////////////////////////* //TC05: Validation of Forgot Password link 
   @Test(priority = 5)	 				
	public void validateForgotPwdLink() throws Exception{
		System.out.println("In validateForgotPwdLink method");	
		gm_WaitVIsibility(lognObj.getRegistration_Lnk(), 30);
		if(lognObj.getFrgtPwd_Lnk().isDisplayed() && lognObj.getFrgtPwd_Lnk().isEnabled()){
			ForgotPassword frgtPassword = lognObj.validateForgotPwdLnk();
			String frgtPwdPageActualTitle = frgtPassword.getForgotPwdPageTitle();
			String frgtPwdExpectedTitle = "Forgot Your Password ";
			String elementName = "Forgot Password Page Title";
			try{
				Assert.assertEquals(frgtPwdPageActualTitle, frgtPwdExpectedTitle);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally{
				System.out.println("in final method");
				System.out.println(frgtPwdPageActualTitle);
				gm_generateReportWithScreenshots_mvn(frgtPwdPageActualTitle, frgtPwdExpectedTitle, elementName);
			}
		}else{
			System.out.println("Forgot Password link is now working");
		}
		System.out.println("Out validateRegistrationLink method");
}	
	*/
														 
}
