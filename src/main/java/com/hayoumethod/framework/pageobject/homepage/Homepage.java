package com.hayoumethod.framework.pageobject.homepage;


import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.pageobject.account.LoginPage;
//import com.hayoumethod.framework.pageobject.account.RegistrationPage;
//import com.hayoumethod.framework.pageobject.insight.InsightLanding;
//import com.hayoumethod.framework.pageobject.product.BeautyRestorerDetail;
//import com.hayoumethod.framework.pageobject.product.ProductLanding;
//import com.hayoumethod.framework.pageobject.rituals.BeautyRestorerRitual;
//import com.hayoumethod.framework.pageobject.rituals.RitualsLanding;
import com.hayoumethod.framework.pageobject.account.RegistrationPage;
import com.hayoumethod.framework.pageobject.product.BeautyRestorerDetail;
import com.hayoumethod.framework.pageobject.product.ProductLanding;
import com.hayoumethod.framework.pageobject.rituals.BeautyRestorerRitual;
import com.hayoumethod.framework.pageobject.rituals.RitualsLanding;
import com.hayoumethod.framework.pageobject.insight.InsightLanding;

//<properties>
//<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>/
//</properties>

public class Homepage extends UI_GenericMethods1{

	/**
	 * @param args
	 */
	 //UI_GenericMethods gmObj = new UI_GenericMethods();
	///*
	//public Homepage(WebDriver driverObj){
	 //this.driverObj = driverObj;
	//}
	// */
	
	
	//Top nav link - 
    @FindBy(xpath="//ul[@id='ui-id-2']/li/a") 
	private static List <WebElement> Top_Nav_links;
    
    //Sub nav link - 
    @FindBy(xpath="//ul[@id='ui-id-1']/li") 
    public static List <WebElement> Sub_nav_links;
 
	//LearrnHowLink
	//@FindBy(xpath="//a[contains (@href, 'what-is-the-hayou-method')]") <---- Not working need to figure out why
	@FindBy(xpath="//a[@class='btn animate']") 
	public static WebElement LearnHow_Btn;
		
	//Shop Beauty Restorer
	@FindBy(xpath="//div[@class='homeLeftBox']//a") 
	public static WebElement shopBeautyRestorer_Btn;
	
	//Shop Body Restorer
	@FindBy(xpath="//div[@class='homeRightBox']//a") 
	public static WebElement shopBodyRestorer_Btn;
	
	//In Practice Button
	@FindBy(xpath="//ul[@class='cat-blocks']/li[1]//a") 
	public static WebElement inPractice_Btn;
	
	//Address Your Stress Button
	@FindBy(xpath="//ul[@class='cat-blocks']/li[2]//a") 
	public static WebElement addressYourStress_Btn;
	
	//Wisdom Button
	@FindBy(xpath="//ul[@class='cat-blocks']/li[3]//a") 
	public static WebElement wisdom_Btn;
	
	//In Practice Block
	@FindBy(xpath="//a[@href='https://www.hayoumethod.com/insight/what-is-the-hayou-method']") 
	public static WebElement inPractice_Blk;
	
	//Stress block
	@FindBy(xpath="//a[@href = 'https://www.hayoumethod.com/insight/stress-the-modern-day-killer']") 
	public static WebElement stress_Blk;
	
	//Gua Sha uncovered block
	@FindBy(xpath="//a[@href='https://www.hayoumethod.com/insight/gua-sha-uncovered']") 
	public static WebElement guaShaUncovered_Blk;
	
	//ExploreInsight button
	@FindBy(xpath="//a[contains(.,'explore insight')]") 
	public static WebElement exploreInsight_Btn;

	//Header link - My cart
	@FindBy(xpath="//div[@data-block='minicart']") 
	public static WebElement myCart_link;
	
	//Default My cart -- Default text
	@FindBy(xpath="//div[@class='block-content']//strong") 
	public static WebElement myCartdflt_txt;
	
	//Default My cart -- CloseButton
	@FindBy(xpath="//div [@class='block-content']/button") 
	public static WebElement mycrtclose_btn;
	
	//Login window
	@FindBy(xpath="//a[contains(.,'User Login')]") 
	public static WebElement usrLogin_lnk;
	
	//My Cart - Product Counter
	@FindBy(xpath="//span[@class='counter qty']/span") 
	public static WebElement myCartCounter_txt;
	
	//My Cart - Product Counter
	@FindBy(xpath="//span[@class='counter-number']/span") 
	public static WebElement myCartCounterr_txt;
	
	
	/*
	//ProductLink
	@FindBy(xpath="//div[@class='footerLinkBox']//li/a") 
	private static product_Btn;
	product_Btn.click();
	
	*/
	
////a[contains (@href, 'https://www.hayoumethod.com/insight/#cat1/')]
    
 
	
	public LoginPage navigateToLoginPage(){
		System.out.println("In navigateToLoginPage() Method ");
		driverObj.navigate().to("https://hayoumethod.com/my-account/");
		LoginPage lognObj = PageFactory.initElements(driverObj, LoginPage.class);
		System.out.println("Out Of navigateToLoginPage() Method ");
		return lognObj;
	}
	
	public RegistrationPage navigateToRegistrationPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/customer/account/create/");
		RegistrationPage registrationObj = PageFactory.initElements(driverObj, RegistrationPage.class);
		return registrationObj;
	}
	
	public ProductLanding navigateToProductPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/product.html/");
		ProductLanding productObj = PageFactory.initElements(driverObj, ProductLanding.class);
		return productObj;
	}
	
	public BeautyRestorerDetail navigateToBeautyRestorerPage(){
		
		driverObj.navigate().to("");
		BeautyRestorerDetail beautyRestorerObj = PageFactory.initElements(driverObj, BeautyRestorerDetail.class);
		return beautyRestorerObj;
	}

	public RitualsLanding navigateToRitualsLandingPage(){
		
		driverObj.navigate().to("https://www.hayoumethod.com/the-rituals/");
		RitualsLanding ritualLandObj = PageFactory.initElements(driverObj, RitualsLanding.class);
		return ritualLandObj;
	}
	
	public BeautyRestorerRitual navigateToBeautyRestorerRitualPage(){
		
		driverObj.navigate().to("https://www.hayoumethod.com/the-beauty-restorer-ritual/");
		BeautyRestorerRitual beautyRestorerRitualObj = PageFactory.initElements(driverObj, BeautyRestorerRitual.class);
		return beautyRestorerRitualObj;
	}
	
	public InsightLanding navigateToInsightLPage(){
		
		driverObj.navigate().to("https://www.hayoumethod.com/insight/");
		InsightLanding insghtLPageObj = PageFactory.initElements(driverObj, InsightLanding.class);
		return insghtLPageObj;
	}
	
//	public InsightLanding navigateToCheckoutPaymentPage(){
		
//		driverObj.navigate().to("https://www.hayoumethod.com/insight/");
	//	InsightLanding insghtLPageObj = PageFactory.initElements(driverObj, InsightLanding.class);
	//	return insghtLPageObj;
	//}
	
	public static String getActualURL(WebElement element) throws IOException, InterruptedException{
		System.out.println("In getActualURL Method");
		System.out.println(element.isDisplayed());
		element.click();
		Thread.sleep(25000);
		String actualUrl = driverObj.getCurrentUrl();
		return actualUrl;
	}
    
	public static String getActualURLs(WebElement element) throws Exception{
		Actions actObj = new Actions(driverObj);
		actObj.moveToElement(element).build().perform();
		Thread.sleep(10000);
		actObj.click(element).build().perform();
		Thread.sleep(30000);
		String actualUrl = driverObj.getCurrentUrl();
		return actualUrl;
	}
	//To Validate the product counter in quick cart icon
	/*

	public  String myCartValidateProdctCountr(String expTxt){
		//Integer expTxt = Integer.parseInt(exp);
		
		String prdCounter = null;
		if(myCartCounter_txt.isDisplayed()== true){
			prdCounter = myCartCounter_txt.getText();
		}
		else{
			 prdCounter = "0";
		}
		System.out.println("ProductCounter "+prdCounter);
		try {
			gmObj.gm_Validate(prdCounter, "myCartIcon", expTxt);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prdCounter;
	}
	
*/
	//This is just to get the Product count from quickcart
	public  String myCartGetProdctCountr(){
		String prdCounter = null;
		prdCounter = myCartCounter_txt.getText();
		System.out.println("ProductCounter = "+prdCounter);
		return prdCounter;
	}
	
//This mthod is to add the initial count to newly added count and returns the total count
	public  String myCartValidateAddProdctCountr(String initialProdCounter, String selectProdQty){
			Integer initialProdCounter_int = 	Integer.parseInt(initialProdCounter);
			Integer finalProdCountedd_Exp = initialProdCounter_int + Integer.parseInt(selectProdQty);
			String finalProdCount_Expd= Integer.toString(finalProdCountedd_Exp);
		//	selectDropdownValue("selectByVisibleText", selectProdQty);
			return finalProdCount_Expd;
		}
		
	
	

}
