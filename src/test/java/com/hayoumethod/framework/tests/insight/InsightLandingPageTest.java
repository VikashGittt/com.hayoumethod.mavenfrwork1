package com.hayoumethod.framework.tests.insight;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.pageobject.account.LoginPage;
import com.hayoumethod.framework.pageobject.homepage.Homepage;
import com.hayoumethod.framework.pageobject.insight.InsightLanding;
import com.hayoumethod.framework.pageobject.rituals.BeautyRestorerRitual;
import com.hayoumethod.framework.pageobject.rituals.RitualsLanding;

public class InsightLandingPageTest extends UI_GenericMethods1{

	UI_GenericMethods1 gmObj;
	Homepage homeObj;
	LoginPage lognObj; 
	RitualsLanding ritualLandingPg;
	BeautyRestorerRitual beautyRestoretRitualPg; 
	InsightLanding insightLandingPg; 
	
	
    @BeforeMethod
    @Parameters({ "Browser", "URL" })
    public void setup(String Browser, String URL) throws IOException{
    	System.out.println("Launch "+URL+" on "+Browser);
       	try {
			homeObj = gmObj.gm_OpenApp(Browser, URL);
			System.out.println("*******Homepage is in display*******");
			insightLandingPg = homeObj.navigateToInsightLPage();
			System.out.println("*******Insight Landing Page is in display*******");
 		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
   @AfterMethod
   public void setup() throws Exception{	   
	   	System.out.println("closing now");
	   	driverObj.quit();
    }
 
   /////////////////////////////////Validation Of Tab Functionality////////////////////////////////////
   @Test (priority = 1)
   public void validateTabFunctnlty() throws IOException, InterruptedException, InvalidFormatException{
	   System.out.println("Out validateTabFunctnlty Method");
	   
	   WebElement element1 = insightLandingPg.all_tab;
	   String actualURL1 = insightLandingPg.handleTabs(element1);
	   String expectedURL1 = "https://www.hayoumethod.com/insight/";
	   String elementName1 = "All Tab";
	   ArrayList <String> actualURLList = new ArrayList <String>();
	   ArrayList <String> expectedURLList = new ArrayList <String>();
	   ArrayList <String> elementName = new ArrayList <String>();
	   actualURLList.add(actualURL1);
	   expectedURLList.add(expectedURL1);
	   elementName.add(elementName1);
	   
	   WebElement element2 = insightLandingPg.all_InPractice;
	   String actualURL2 = insightLandingPg.handleTabs(element2);
	   String expectedURL2 = "https://www.hayoumethod.com/insight/";
	   String elementName2 = "In Practice Tab";
	   actualURLList.add(actualURL2);
	   expectedURLList.add(expectedURL2);
	   elementName.add(elementName2);
	   
	   WebElement element3 = insightLandingPg.all_InPractice;
	   String actualURL3 = insightLandingPg.handleTabs(element3);
	   String expectedURL3 = "https://www.hayoumethod.com/insight/";
	   String elementName3 = "Address your stress Tab";
	   actualURLList.add(actualURL3);
	   expectedURLList.add(expectedURL3);
	   elementName.add(elementName3);
	   
	   WebElement element4 = insightLandingPg.all_Wisdom;
	   String actualURL4 = insightLandingPg.handleTabs(element4);
	   String expectedURL4 = "https://www.hayoumethod.com/insight/";
	   String elementName4 = "Wisdom";
	   actualURLList.add(actualURL4);
	   expectedURLList.add(expectedURL4);
	   elementName.add(elementName4);
		 try {
				Assert.assertEquals(actualURLList.toString(), expectedURLList.toString());
			}	catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally{
				System.out.println("in finally method");
				System.out.println(actualURLList.toString());
				System.out.println(expectedURLList.toString());
				gm_generateReportWithScreenshots_mvn(actualURLList.toString(), expectedURLList.toString(), elementName.toString());
			}
		 System.out.println("Out validateTabFunctnlty Method");
	}


/////////////////////////////////////////Validate Intro Block Functionality/////////////////////////////////////
   @Test (priority = 2)	
	public void  validateIntroBlockFunc() throws Exception{
		System.out.println(" In validateAnxietyBlockFunc Method");
		ArrayList<String>  actualIntroBlock  = new ArrayList<String>();
		ArrayList<String>  expectedIntroBlock  = new ArrayList<String>();
		ArrayList<String>  elementNameList  = new ArrayList<String>();

		WebElement element1 = driverObj.findElement(By.xpath("//article/h1"));
		String actualBlockTitle1 = insightLandingPg.validateBlockTitle(element1);
		String expectedBlockTitle1 = "In Practice";
		String elementName1 = "Page Title";

		actualIntroBlock.add(actualBlockTitle1);
		expectedIntroBlock.add(expectedBlockTitle1);
		elementNameList.add(elementName1);
		
		
		WebElement element2 = driverObj.findElement(By.xpath("//article/p[1]"));
		String actualDateStamp1 = insightLandingPg.validateDateStamp(element2);
		String expectedDateStamp1 = "Posted on July 03, 2017: In Practice ";
		String elementName2 = "Date Stamp";

		actualIntroBlock.add(actualDateStamp1);
		expectedIntroBlock.add(expectedDateStamp1);
		elementNameList.add(elementName2);
		
		WebElement element3 = driverObj.findElement(By.xpath("//div[@class='details']/p"));
		String actualWriterName1 = insightLandingPg.validateWriterName(element2);
		String expectedWriterName2 = "Article by Den";
		String elementName3 = "WriterName";

		actualIntroBlock.add(actualWriterName1);
		expectedIntroBlock.add(expectedWriterName2);
		elementNameList.add(elementName2);
		
		
		WebElement element4 = driverObj.findElement(By.xpath("//div[@class='details']/p"));
		String actualBlockDesc1 = insightLandingPg.validateBlockTitle(element2);
		String expectedBlockDesc1 = "Katie Brindle is a Practitioner of Chinese Medicine. This 'In Practice’ section highlights just some of the conditions that Katie has treated in her clinic over the years. Stress is the underlying common link in many of these health issues. It was this discovery that led her to create Hayo’u – easy tools and techniques to manage stress.";
		String elementName4 = "Block Desc.";

		actualIntroBlock.add(actualBlockDesc1);
		expectedIntroBlock.add(expectedBlockDesc1);
		elementNameList.add(elementName4);

		WebElement element5 = driverObj.findElement(By.xpath("//div[@id='top_article']//a"));
		String actualURL1 = insightLandingPg.validateBlockDesc(element5);
		String expectedURL1 = "https://www.hayoumethod.com/insight/what-is-the-hayou-method/";
		String elementName5 = "ReadMore Button";
		actualIntroBlock.add(actualURL1);
		expectedIntroBlock.add(expectedURL1);
		elementNameList.add(elementName5);
		if(actualURL1.contains(expectedURL1)){
			System.out.println(element5+" is directed to correct page having URL "+actualURL1);
		}else{
			System.out.println(element5+" is directed to wrong page with URL"+actualURL1);
		} 

		try {
			Assert.assertEquals(actualIntroBlock.toString(), expectedIntroBlock.toString());
		}	catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally{
			System.out.println("in finally method");
			System.out.println(actualIntroBlock.toString());
			gm_generateReportWithScreenshots_mvn(actualIntroBlock.toString(), expectedIntroBlock.toString(), elementNameList.toString());
		}
		System.out.println(" Out validateAnxietyBlockFunc Method");
}

   /////////////////////////////////////////Validate Anxiety Block Functionality/////////////////////////////////////
   @Test (priority = 3)
	public void  validateAnxietyBlockFunc() throws Exception{
		 System.out.println(" In validateAnxietyBlockFunc Method");
		ArrayList<String>  actualAnxietyBlock  = new ArrayList<String>();
		ArrayList<String>  expectedAnxietyBlock  = new ArrayList<String>();
		ArrayList<String>  elementNameList  = new ArrayList<String>();
		
		WebElement element1 = insightLandingPg.insightBlock.findElement(By.xpath("//article/h3"));
		String actualBlockTitle1 = insightLandingPg.validateBlockTitle(element1);
		String expectedBlockTitle1 = "Anxiety";
		String elementName1 = "Block Title";

		actualAnxietyBlock.add(actualBlockTitle1);
		expectedAnxietyBlock.add(expectedBlockTitle1);
		elementNameList.add(elementName1);
		
		WebElement element2 = insightLandingPg.insightBlock.findElement(By.xpath("//article//div[@class='brief-desc']"));
		String actualBlockDesc1 = insightLandingPg.validateBlockTitle(element2);
		String expectedBlockDesc1 = "The Eastern approach to Anxiety - an illness of the body not the mind.... ";
		String elementName2 = "Block Desc.";
		
		actualAnxietyBlock.add(actualBlockDesc1);
		expectedAnxietyBlock.add(expectedBlockDesc1);
		elementNameList.add(elementName2);
		
		WebElement element3 = insightLandingPg.insightBlock.findElement(By.xpath("//div[@class='postDetailBtn']/a"));
		String actualURL1 = insightLandingPg.validateBlockDesc(element3);
		String expectedURL1 = "https://www.hayoumethod.com/insight/anxiety/";
		String elementName3 = "ReadMore Button";
		actualAnxietyBlock.add(actualURL1);
		expectedAnxietyBlock.add(expectedURL1);
		elementNameList.add(elementName3);
		if(actualURL1.contains(expectedURL1)){
			System.out.println(element3+" is directed to correct page having URL "+actualURL1);
		}else{
			System.out.println(element3+" is directed to wrong page with URL"+actualURL1);
		} 
		
		 try {
				Assert.assertEquals(actualAnxietyBlock.toString(), expectedAnxietyBlock.toString());
			}	catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally{
				System.out.println("in finally method");
				System.out.println(actualAnxietyBlock.toString());
				gm_generateReportWithScreenshots_mvn(actualAnxietyBlock.toString(), expectedAnxietyBlock.toString(), elementNameList.toString());
			}
		 System.out.println(" Out validateAnxietyBlockFunc Method");
	}
		


}
