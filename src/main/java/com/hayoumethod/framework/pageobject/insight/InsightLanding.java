package com.hayoumethod.framework.pageobject.insight;


import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.pageobject.homepage.Homepage;

public class InsightLanding extends UI_GenericMethods1 {

	//The Insight Page Title 
	@FindBy(how = How.XPATH, using ="//article/h1")
	public static WebElement pageTitle;

	 //The Insight Page text1 
	@FindBy(how = How.XPATH, using ="//article/p[1]")
	public static WebElement dateStamp;
	  
	 //The Insight Page text2 
	@FindBy(how = How.XPATH, using ="//article/p[2]")
	public static WebElement writerName;
	  
	 //The Insight Page description
	@FindBy(how=How.XPATH, using = "//article/div/p")
	public static WebElement insightPageDescription;
	  

	 //The Read More Button
	@FindBy(how = How.XPATH, using ="//article//a")
	public static WebElement readMore_Btn;
	  

	 //Tab All
	@FindBy(how = How.XPATH, using ="//ul[@id='accordion-2']//a[contains(., 'All')]")
	public static WebElement all_tab;

	 //Tab In Practice
	@FindBy(how = How.XPATH, using ="//ul[@id='accordion-2']//a[contains(., 'In Practice')]")
	public static WebElement all_InPractice;

	 //Tab Address your stress
	@FindBy(how = How.XPATH, using ="//ul[@id='accordion-2']//a[contains(., 'Address your stress')]")
	public static WebElement all_Addressyourstress;

	 //Tab Wisdom
	@FindBy(how = How.XPATH, using ="//ul[@id='accordion-2']//a[contains(., 'Wisdom')]")
	public static WebElement all_Wisdom;
 
	//Load More Button
	@FindBy(how = How.XPATH, using ="//div[@class='relatedBtnWrap']/a")
	public static WebElement loadMore_btn;
	

	// readMore
	@FindBy(how = How.XPATH, using ="//li//div[@class='postDetailBtn']/a")
	public static WebElement readMore_btn;
	
	//Block ul id="masonryDiv2DFG"
	@FindBy(xpath = "//ul[@id='masonryDiv2DFG']/li[1]")
	public static WebElement insightBlock;
	
	
	public String handleTabs(WebElement ele) throws IOException, InterruptedException{
		Thread.sleep(30000);
		    System.out.println("In handleTabs Method");
			String actualTabName = ele.getText();
			ele.click();
			Thread.sleep(30000);
			System.out.println("Clicked Tab - "+actualTabName);
			String actualURL =  driverObj.getCurrentUrl();
			System.out.println("actualURL - "+actualURL);
		    System.out.println("Out handleTabs Method");
			return actualURL;

	}
	
	public String validateDateStamp(WebElement element) throws InvalidFormatException, IOException, InterruptedException{
		Thread.sleep(30000);
		gm_WriteToLog("In validateDateStamp method", "I");
		System.out.println("In validateDateStamp method");
		 
		String actualDateStamp = element.getText();
		gm_WriteToLog("Out validateDateStamp method", "I");
		System.out.println("Out validateDateStamp method");
		return actualDateStamp;
	}
	
	public String validateWriterName(WebElement element) throws InvalidFormatException, IOException, InterruptedException{
		Thread.sleep(30000);
		System.out.println("In validateWriterName method");
		 
		String actualWriterName = element.getText();
		System.out.println("Out validateWriterName method");
				return actualWriterName;
	}
	
	public String validateBlockTitle(WebElement element) throws InvalidFormatException, IOException, InterruptedException{
		Thread.sleep(30000);
		System.out.println("In validateBlockTitle method");
		 
		String actualBlockTitle = element.getText();
		System.out.println("Out validateBlockTitle method");
				return actualBlockTitle;
	}
	
	public String validateBlockDesc(WebElement element) throws InvalidFormatException, IOException, InterruptedException{
		Thread.sleep(30000);
		System.out.println("In validateBlockDesc method");
		 
		String actualBlockDesc = element.getText();
		System.out.println("Out validateBlockDesc method");
				return actualBlockDesc;
	}
	
	public static String validateReadMoreButton(WebElement element)  throws Exception{
		Thread.sleep(30000);
		System.out.println("In validateReadMoreButton Method");
				 
					Actions actObj = new Actions(driverObj);
					actObj.click(element).build().perform(); 
					 
					Thread.sleep(30000);
					String actualURL = driverObj.getCurrentUrl();
					System.out.println("Out validateReadMoreButton Method");
					return actualURL; 
	}
	
	/*
	if(url.contains(expURL)){
		System.out.println(elementName+" is directed to correct page having URL "+url);
	}else{
		System.out.println(elementName+" is directed to wrong page with URL"+url);
	} 
	
	}else{
		System.out.println();
	}
	
		gm_ValidateText(we1, "Block Title", "Weight Loss");
		
		WebElement we2 = 
		gm_ValidateText(we2, "Block Desc.", "Why canï¿½t I lose weight?");
		
		WebElement readMoreBtn = insightBlock.findElement(By.xpath("//div[@class='postDetailBtn']/a"));
		
	 
		
		//div class="scroll-animate animate-on"
		
	}
	
	*/
	
	
}
