package com.hayoumethod.framework.pageobject.account;


import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.genericclasses.XL_GenericMethods;
import com.hayoumethod.framework.pageobject.account.ForgotPasswordPage;
import com.hayoumethod.framework.pageobject.account.RegistrationPage;
import com.hayoumethod.framework.pageobject.myaccount.AccountDashboard;

public class LoginPage extends UI_GenericMethods1{
	
	
	//PageTitle
	
    @FindBy(xpath="//h1[contains(text(), 'Customer Login')]")
    public static  WebElement PageTitle_txt;
    
    //Email field
    @FindBy(xpath="//input[@id = 'username']")
    public static WebElement Email_tb;
    
    //Password field
    @FindBy(xpath="//input[@id='password']")
    public static WebElement Password_tb;
    
    //SignIn Button 
    @FindBy(xpath="//input[@type='submit']") 
    public static WebElement SignIn_Btn;
    
    //Registration Link
    @FindBy(xpath="//a[@class='action create primary']")
    public static WebElement registration_Lnk;
    
    //Forgot password Link
    @FindBy(xpath="//a[@class='action remind']") 
    public static WebElement frgtPwd_Lnk;
    
	public LoginPage(){
		System.out.println("1");
		TestDataPath1="TestData/LoginPage/LoginPage.xlsx";
   		TDSheetName1="Login_Page";
    	System.out.println("2");
    }
	/*public LoginObject(WebDriver driverObj){
	    this.driverObj = driverObj;
	    
	  //  PageFactory.initElements(driverObj, this);
	}
    */
///*	
    public AccountDashboard validateLogin(String rownum) throws IOException, InterruptedException{
		System.out.println("*******In validateLogin method*********");
		XL_GenericMethods.xl_GetTestData1(rownum);
		System.out.print("*******Data reading done from Testdata sheet*******");
		gm_XLInput1(Email_tb, "Email_tbx", "Email_tbox", 30);
		System.out.println("*******Value entered in email field*******");
    	gm_XLInput1(Password_tb, "Password_tbx", "Password_tbox", 30);
    	System.out.println("*******Value entered in password field*******");
    	Thread.sleep(20000);
    	SignIn_Btn.click();
    	System.out.println("*******Signin button clicked*******");
    	
    	AccountDashboard  accntDboardPageObj=PageFactory.initElements(driverObj, AccountDashboard.class);
    	System.out.println("*******Login done and user navigated to Account Dashboard page*******");
    	System.out.println("*******Out Of  validateLogin method*******");
    	return accntDboardPageObj; 
	}
	
	public RegistrationPage validateCreateAccntLnk() throws Exception{
		
		System.out.println("In validateCreateAccntLnk() Method");
		registration_Lnk.click();
		RegistrationPage  regPageObj=PageFactory.initElements(driverObj, RegistrationPage.class);
		return regPageObj;
	}
	
	public ForgotPasswordPage validateForgotPwdLnk() throws Exception{
		ForgotPasswordPage fgtPwdPageObj = null;
		gm_WaitVIsibility(frgtPwd_Lnk, 30);
		if (frgtPwd_Lnk.isDisplayed()){
			frgtPwd_Lnk.click();
			fgtPwdPageObj=PageFactory.initElements(driverObj, ForgotPasswordPage.class);
		}else{
			System.out.println(frgtPwd_Lnk.getText()+" is missing");
		}
		
		return fgtPwdPageObj; 
	}
    /*
	public void validateLoginPageTitle() throws InvalidFormatException, IOException, InterruptedException{
		System.out.println("Inside validateTitles method");
		gm_ValidateText(getPageTitle_txt(), "PageTitle", "Customer Login");
		
		
		//Child class can call parent none static method either directly of by ref of 
		//child/parent class object but can not call by providing class ref. 
	}
	*/

	
	



}
