package com.hayoumethod.framework.genericclasses;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hayoumethod.framework.pageobject.homepage.Homepage;


	public class UI_GenericMethods1 extends XL_GenericMethods {
	public static final String ConfigFilePath="config/config.properties";
	protected static WebDriver driverObj; 
	public static Properties ConfigObject;

	String locatorType1;
	String value1;
	static String ele_xpathh;
	static String ele_idd;
	static String ele_Name;
	static String ele_className;
	static String name;
	static String ele_linkText;
	
	
////////////////////////////////////Log Configuration//////////////////////////////////////////
	public void gm_ConfigureLog4J() throws IOException{
		
		Properties p = new Properties();
		String log4jPath=gm_GetConfigValue("log4JPropPath");
	    p.load(new FileInputStream(log4jPath));
	    PropertyConfigurator.configure(p);
	}
	public  void gm_WriteToLog(String Msg, String LogType) throws IOException{
		
		   Logger logger = Logger.getLogger(this.getClass());
		   if(LogType.equalsIgnoreCase("I")){
				logger.info(Msg);
		   }else if(LogType.equalsIgnoreCase("D")){
				logger.debug(Msg);
		   }else if(LogType.equalsIgnoreCase("E")){
				logger.error(Msg);
		   }else if(LogType.equalsIgnoreCase("W")){
				logger.warn(Msg);
		   }
	
	}
	public  void gm_WriteToLog(String Msg,String LogType, String ClassName) throws IOException{
		ConfigObject=new Properties();
		FileInputStream fis=new FileInputStream(ConfigFilePath);
		ConfigObject.load(fis);
	}
	public  void gm_LoadCOnfigFile() throws IOException{
		ConfigObject=new Properties();
		FileInputStream fis=new FileInputStream(ConfigFilePath);
		ConfigObject.load(fis);
	}
	public  String gm_GetConfigValue(String propName) throws IOException{
		if(ConfigObject==null){
			gm_LoadCOnfigFile();
		}
		String PropValue=ConfigObject.getProperty(propName);
		return PropValue;
	}
	/********************Wait Visibility**************************/
	public void gm_WaitVIsibility(WebElement we, int timeOut_Sec){
	WebDriverWait wtObj = new WebDriverWait(driverObj, timeOut_Sec);	
	wtObj.until(ExpectedConditions.visibilityOf(we));
	
	}
					
					/********************Wait Clickable**************************/
	public void gm_WaitClickable(WebElement we, int timeOut_Sec){
	WebDriverWait wtObj=new WebDriverWait(driverObj, timeOut_Sec);	
	wtObj.until(ExpectedConditions.elementToBeClickable(we));
	
	}

 ///////////////////////// This method to be replaced by Fluent Wait
	public void gm_WaitVIsibility_Java(WebElement we, int timeOut_Sec)  {
		driverObj.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	    int time=0;
		   while(gm_isVisible(we)==false && time>=timeOut_Sec){
					   
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					   time=time+1;
		   }

		driverObj.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}	

/****************************************Launch Browser & URL ****************************************************/	

						/********************Launch Browser**************************/
	public void gm_LaunchBrowser(String browserName) throws IOException{
		
		  		//Launch IE11 browser
			if(browserName.equalsIgnoreCase("IE")==true){
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer_64.exe");
				driverObj= new InternetExplorerDriver();
				gm_WriteToLog("IE has been launched", "I");
				 
				//Launch Chrome browser
			}else if(browserName.equalsIgnoreCase("CH")==true){
				System.setProperty("webdriver.chrome.driver", "G:\\QA\\AutomationTools\\Selenium\\WorkspaceMars1\\MasterFiles\\Drivers\\DriversAllinOne\\Chromedriver_win32_v2.38\\chromedriver.exe");
				driverObj= new ChromeDriver();
				gm_WriteToLog("Chrome has been launched", "I");
	 
			  //Launch Firefox browser
			}else if(browserName.equalsIgnoreCase("FF")==true){
				System.setProperty("webdriver.gecko.driver", "G:\\QA\\AutomationTools\\Selenium\\WorkspaceMars1\\MasterFiles\\Drivers\\GeckoDriver\\64Bit\\v20\\geckodriver.exe");
				//ProfilesIni profile = new ProfilesIni();
				//FirefoxProfile myprofile = profile.getProfile("profileMavenProjects");
				driverObj= new FirefoxDriver(/*myprofile*/);
				gm_WriteToLog("Firefox has been launched", "I");
		 
			}else if(browserName.equalsIgnoreCase("Safari")==true){
				System.out.println("//Need to write code here for Safari");
			}else if(browserName.equalsIgnoreCase("Opera")==true){
				System.out.println("//Need to write code here for Opera");
			}else{
				System.out.println("Please enter correct browser name");
			}
			driverObj.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driverObj.manage().window().maximize();
			
		
	}
						/********************Open Site**************************/
	public void gm_OpenURL(String URL){
		driverObj.get(URL);
	}

	public Homepage  gm_OpenApp(String BrowserName, String URL) throws IOException, InterruptedException {
		gm_LaunchBrowser(BrowserName);
		gm_OpenURL(URL);
		//WebElement allowCookie_btn = driverObj.findElement(By.xpath("//button[@id='btn-cookie-allow']"));
		//System.out.println("Before - Allow cookie - Click");
		//gm_WaitVIsibility_Java(allowCookie_btn, 60);
		//gm_WaitClickable(allowCookie_btn, 30);
		//if (allowCookie_btn.isDisplayed()==true){
		//	System.out.println("In If");
		//	allowCookie_btn.click();
		//	System.out.println("After - Allow cookie - Click");
		//}else{
		//	gm_WaitClickable(allowCookie_btn, 30);
		//	allowCookie_btn.click();
		//	System.out.println("In Else");
		//	System.out.println("After - Allow cookie - Click");
		//}
		System.out.println("Now Homepage is in display");
		Homepage homeObj=PageFactory.initElements(driverObj, Homepage.class);
		//Navigation NavObject=PageFactory.initElements(driverObj, Navigation.class);
	  return homeObj;
	}
	 


											///********************Input Method**************************\
	
	////////////////////////////////Text Field///////////////////////////////////////
	public  String inputValuesTextField(WebElement element, String inputVal){
		try{
			element.clear();
			element.sendKeys(inputVal);
			gm_WriteToLog(inputVal+ "has been entered in "+element, "I");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	    return inputVal;
	}
	
	public void gm_Input(WebElement we, String inputValue, String WEName, int timeout_Seconds) throws IOException{
		try{
			we.clear();
			we.sendKeys(inputValue);
			gm_WriteToLog(inputValue+ "has been entered in "+WEName, "I");
		}catch(NoSuchElementException ne){
			gm_WaitVIsibility(we, timeout_Seconds);	
		}catch(ElementNotVisibleException nv){
			gm_WriteToLog(WEName+" is not visible", "E");
		}catch(StaleElementReferenceException se){
			System.out.println(se.getMessage());
		}
			
	}
	
	public void gm_XLInput1(WebElement we, String ColumnName_Key,String WEName, int timeout_Seconds) throws IOException{
		try{
			
			String InputValue=(String) testData_HM.get(ColumnName_Key);
			String TagName=we.getTagName();
			String TypeName = we.getAttribute("type");
			if(TagName.equalsIgnoreCase("select")){
				Select selectObj=new Select(we);
				int indexnum=-1;
					try{
					     indexnum=Integer.parseInt(InputValue);	
					}catch(Exception e){
						
					}
					if(indexnum==-1){
						try{
							   selectObj.selectByVisibleText(InputValue);   
							   gm_WriteToLog("Select By VisibleText", "I");
						   }catch(Exception e){
							   selectObj.selectByValue(InputValue);
							   gm_WriteToLog("Select By Value", "I");
						   }
					}else{
						selectObj.selectByIndex(indexnum);  
						 gm_WriteToLog("Select By Index", "I");
					}
		
			}else if(TypeName.equalsIgnoreCase("radio")||TypeName.equalsIgnoreCase("checkbox")){
				we.click();
			}else{
			 
				we.clear();
				we.sendKeys(InputValue);
			}
		
		gm_WriteToLog(InputValue+ "has been entered in "+WEName, "I");
		}catch(NoSuchElementException ne){
			gm_WaitVIsibility(we, timeout_Seconds);	
			System.out.println(ne.getMessage());
			
		}catch(ElementNotVisibleException nv){
			gm_WriteToLog(WEName+" is not visible", "E");
			System.out.println(nv.getMessage());
		}catch(StaleElementReferenceException se){
			System.out.println(se.getMessage());
		}
			
	}
		

							/********************Is Visible**************************/
				
	public Boolean gm_isVisible(WebElement we){
				
		Boolean visibility=we.isDisplayed();
		return visibility;
	}


							/********************Is Enabled **************************/

	public Boolean gm_checkEnabledStatus(WebElement element){
	
		Boolean visibility=element.isEnabled();
		return visibility;
	}

							/********************Is Selected**************************/

	public boolean gm_checkSelectedStatus(WebElement element){
	
		Boolean selectStatus=element.isSelected();
		return selectStatus;
	}

							/********************Select Dropdown**************************/
	public static void selectDropdown_ByIndex(WebElement dropdwnElement, int index){
		
		Select slctDropdown = new Select(dropdwnElement);
		slctDropdown.selectByIndex(index);
			System.out.println("Selected Dropdown option is: "+slctDropdown.getFirstSelectedOption().getText());
	} 
	
							
	public static void selectDropdown_SelectAll(WebElement dropdwnElement){
		
		Select slctDropdown = new Select(dropdwnElement);
		List <WebElement> drpdwnOptions = slctDropdown.getOptions();
		int optionCount = drpdwnOptions.size();
		for(int i=0; i<optionCount; i++){
			WebElement  drpDwnEle = drpdwnOptions.get(i);
			System.out.println("Selected Dropdown option is: "+slctDropdown.getFirstSelectedOption().getText());
		}
	}
	
	public static void selectDropdown_ByVisibleText(WebElement dropdwnElement, String visibleTextValue){
		 
			Select slctDropdown = new Select(dropdwnElement);
			//List <WebElement> drpdwnOptions = slctDropdown.getOptions(); --Not needed it seems thats why commented here
			//int optionCount = drpdwnOptions.size();
			slctDropdown.selectByVisibleText(visibleTextValue);
			System.out.println("Selected Dropdown option is: "+slctDropdown.getFirstSelectedOption().getText());
	}
	
	public static void selectDropdown_ByValue(WebElement dropdwnElement, String value){
		 
		    Select slctDropdown = new Select(dropdwnElement);
			//List <WebElement> drpdwnOptions = slctDropdown.getOptions();  --Not needed it seems thats why commented here
			//int optionCount = drpdwnOptions.size();
			slctDropdown.selectByValue(value);
			System.out.println("Selected Dropdown option is: "+slctDropdown.getFirstSelectedOption().getText());
	}
	 
	 public static void selectDropdown_ById(WebElement dropdwnElement, String id){
		 
		 	Select slctDropdown = new Select(dropdwnElement);
			//List <WebElement> drpdwnOptions = slctDropdown.getOptions(); --Not needed it seems thats why commented here
			int val = Integer.parseInt(id);
			slctDropdown.selectByIndex(val);
			System.out.println("Selected Dropdown option is: "+slctDropdown.getFirstSelectedOption().getText());
		}

	 

	

	
									/********************Take Screenshot**************************/
	public void gm_TakeSnapshot(WebDriver driver, String destFilePath) throws IOException, InterruptedException{
		 TakesScreenshot tss=(TakesScreenshot) driver;
         File srcfileobj=tss.getScreenshotAs(OutputType.FILE);
         
         Thread.sleep(300);
         File destFileObj=new File(destFilePath);        
		 FileUtils.copyFile(srcfileobj, destFileObj);
	
	}
	public void gm_TakeSnapshot(WebDriver driver, WebElement ElementName) throws IOException, InterruptedException{
		 
		 String destFilePath="Reports/Images/"+ElementName+"_"+fn_GetTimeStamp()+".png";
		 destFilePath=destFilePath.split("/", 2)[1];
			
		 TakesScreenshot tss=(TakesScreenshot) driver;
         File srcfileobj=tss.getScreenshotAs(OutputType.FILE);
         
         Thread.sleep(300);
         File destFileObj=new File(destFilePath);        
		 FileUtils.copyFile(srcfileobj, destFileObj);
	
	}
	///*// This method to be replaced by Assert
	//********************ValidateText**************************\
	public  void gm_ValidateText(WebElement we,String ElementName ,String exp) throws InvalidFormatException, IOException, InterruptedException{
		System.out.println("In gm_ValidateText method");
		String ActualText=we.getText();
		System.out.println("ActualText = "+ActualText);
		System.out.println("ExpectedText = "+exp);
		String status = null;
		if(ActualText.equalsIgnoreCase(exp) == true){
			status = "Passed";
		}else{
			status = "failed";
		}
		gm_Validate(ActualText, exp, ElementName);
		
	}
	
	
	///*///////////////////// This method to be replaced by Assert//
	public  void gm_ValidateText_mvn(WebElement we,String ElementName, String expectedText) throws InvalidFormatException, IOException, InterruptedException{
		System.out.println("In gm_ValidateText_mvn method");
		String actualText=we.getText();
		System.out.println("ActualText = "+actualText);
	
		System.out.println("ExpectedText = "+expectedText);
		String status = null;
		if(actualText.equalsIgnoreCase(expectedText) == true){
			status = "Passed";
		}else{
			status = "failed";
		}
		gm_Validate(actualText, expectedText, ElementName);
		
	}
	public  void gm_XLValidateText(WebElement we, String ElementName, String exp_keyname) throws InvalidFormatException, IOException, InterruptedException{
		String ActualText=we.getText();
		String exp=(String) testData_HM.get(exp_keyname);
		String status = null;
		if(ActualText.equalsIgnoreCase(exp) == true){
			status = "Passed";
		}else{
			status = "failed";
		}
		gm_Validate(ActualText, exp, ElementName);
		
	}
	//*/
	///* Replaced by method gm_generateReportWithScreenshots_mvn(String actualResult, String expectedResult, String elementName)
	// * 
	public void gm_Validate(String actualText, String expectedText, String ElementName) throws InvalidFormatException, IOException, InterruptedException{
	
		System.out.println("In gm_Validate method");
		
		String ImagePath  = null; 
		if(actualText.equalsIgnoreCase(expectedText)){
			ImagePath="Reports/Images/"+ElementName+"_"+fn_GetTimeStamp()+".png";
			ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			String[] ResArray=gm_GetResultStepArray(ElementName, expectedText, actualText, "Passed",ImagePath);
			xl_WriteResultToExcel(ResArray);
		}else{
			 ImagePath="Reports/Images/"+ElementName+"_"+fn_GetTimeStamp()+".png";
			ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			
			String[] ResArray=gm_GetResultStepArray(ElementName, expectedText, actualText, "Failed", ImagePath);
			xl_WriteResultToExcel(ResArray);
		} 
	}
	//*/
							/********************Generate Report with Screenshot**************************/
	
	public void gm_generateReportWithScreenshots_mvn(String actualResult, String expectedResult, String elementName) throws InvalidFormatException, IOException, InterruptedException{
		
		System.out.println(" In gm_generateReportWithScreenshots_mvn Method");
		String ImagePath  = null; 
		if(actualResult.equalsIgnoreCase(expectedResult)){
			ImagePath="Reports/Screenshots/Passed/"+elementName+"_"+fn_GetTimeStamp()+".png";
			//ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			String[] ResArray=gm_GetResultStepArray(elementName, expectedResult, actualResult, "Passed", ImagePath);
			xl_WriteResultToExcel(ResArray);
		}else{
			 ImagePath="Reports/Screenshots/Failed/"+elementName+"_"+fn_GetTimeStamp()+".png";
			//ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			
			String[] ResArray=gm_GetResultStepArray(elementName, expectedResult, actualResult, "Failed", ImagePath);
			xl_WriteResultToExcel(ResArray);
		} 
		System.out.println(" Out gm_generateReportWithScreenshots_mvn Method");
	}
	
	public  String[] gm_GetResultStepArray(String ElementName, String exp, String ActualText, String Status, String SnapshotPath){
	      Throwable t=new Throwable();
	      StackTraceElement[] arr_Ste=t.getStackTrace();
	      for(StackTraceElement ste: arr_Ste){
	    	  System.out.println(ste);
	      }
	      System.out.println("arr_Ste length "+arr_Ste.length);
	      StackTraceElement ste0=arr_Ste[0];
	      System.out.println("ste0: "+ste0);
	      StackTraceElement ste1=arr_Ste[1];
	      System.out.println("ste1: "+ste1);
	      StackTraceElement ste=arr_Ste[2];
	      System.out.println("ste2: "+ste);
 
	      
	      //Get Method name from selected stake
	      String MethodName=ste.getMethodName();
	      String metname1 = MethodName.substring(0,1).toUpperCase();
	      String metname2 = MethodName.substring(1).toLowerCase();
	      System.out.println(metname1.toString());
	      System.out.println(metname2.toString());
	      MethodName = metname1.concat(metname2);
	      
	      //Get Class name from selected stake --To get Module and submodule name by splitting it
	      String ClassName=ste.getClassName().toUpperCase();
	      System.out.println(ClassName.toString());
	      String[]ArrClsName=ClassName.split("\\.");
	      String SubModuleName=ArrClsName[ArrClsName.length-1].toUpperCase();
	      System.out.println(SubModuleName.toString());

	      
	      //Get module name
	      String ModuleName=ArrClsName[ArrClsName.length-2];
	      System.out.println(ModuleName.toString());
	      String modname1 = ModuleName.substring(0,1).toUpperCase();
	      String modname2 = ModuleName.substring(1).toLowerCase();
	      System.out.println(modname1.toString());
	      System.out.println(modname2.toString());
	      ModuleName = modname1.concat(modname2);
	     
	      String[] ResultArray={ModuleName, SubModuleName, MethodName, ElementName, exp, ActualText, Status, SnapshotPath};
 
	      return ResultArray;
	}
	
						/********************Generate Report without Screenshot**************************/
	
	public  String[] gm_GetResultStepArray1(String ElementName, String exp, String ActualText, String Status, String SnapshotPath){
	      Throwable t=new Throwable();
	      StackTraceElement[] arr_Ste=t.getStackTrace();
	      StackTraceElement ste=arr_Ste[3];
	      String MethodName=ste.getMethodName().toUpperCase();
	      String ClassName=ste.getClassName().toUpperCase();
	      String[]ArrClsName=ClassName.split("\\.");
	      String SubModuleName=ArrClsName[ArrClsName.length-1].toUpperCase();
	      String ModuleName=ArrClsName[ArrClsName.length-2];
	      String modname1 = ModuleName.substring(0).toUpperCase();
	      System.out.println("modname1: "+modname1);
	      
	      SnapshotPath = "Snapshot Not Taken for Passed Results";
	      String[] ResultArray={ModuleName, SubModuleName, MethodName,ElementName, exp, ActualText, Status, SnapshotPath};
	    
	      return ResultArray;
	}

	
    										/********************Object Repository***************************/
	
	
	public static void objRep() throws IOException{
		
		Properties obj = new Properties(); 
		
		 FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objects.properties");
		   //Pass object reference objfile to load method of Properties object.	 	
		 obj.load(objfile);
		
		}

    static Properties prop = null;
    static Properties queryProp = null;
    private static final Logger log = Logger.getLogger(UI_GenericMethods1.class.getName());
    static{
           log.info("Loading the Properties File");
           try {
                  
                  prop = new Properties();   
                  prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\objects.properties"));
           } catch (FileNotFoundException fe) {
                  log.error("Exception in PropFileReader--"+fe);
           } catch (IOException io) {
                  log.error("Exception in PropFileReader--"+io);
           }
    }
    
    
    public static String objRep1(String key){
           String value = null;
           try{
           if(prop!=null){      
               value = prop.getProperty(key);
        	
           }else{
                  prop = new Properties();   
                  prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\objects.properties"));
               value = prop.getProperty(key);
           }
           }catch(Exception ex){
                  log.error("Exception in PropFileReader--"+ex);
           }
           return value;
    }
    
    
    
    
    
    //////////////////////Need to Justify the usage of below methods/////////////////////////
    
    
    
	/********************Validate Display Status**************************/
	public static String checkDisplayStatus(WebElement element, String elementName){
		Boolean actualstatus=element.isDisplayed();
		String actualValue= actualstatus.toString();
		return actualValue;
		/*Use Assert method in the Test class to compare expected and actual values
		* along with below method
		*  gm_generateReportWithScreenshots_mvn(actualValue, expValue, ElementName);
		
		*/
	}
								/********************ValidateEnableState**************************/
	public  String gm_ValidateState(WebElement element, String ElementName) throws Exception{
		Boolean actualstatus=element.isEnabled();
		String actualValue= actualstatus.toString();
		return actualValue;
		
		/*Use Assert method in the Test class to compare expected and actual values
		* along with below method
		*  gm_generateReportWithScreenshots_mvn(actualValue, expValue, ElementName);
		* */
	
	}
							/********************ValidateSelectState**************************/
	public String checkSelectedStatus(WebElement element, String elementName) throws Exception{
		Boolean actualStatus = element.isSelected();
		String actualValue= actualStatus.toString();
		return actualValue;
		
		/*Use Assert method in the Test class to compare expected and actual values
		 * along with below method
		 *  gm_generateReportWithScreenshots_mvn(actualValue, expValue, elementName);
		 * */
	}
	
		/////////////Need to Understand this/////////////////
/*	public static void checkEnableStatus(String fieldType, List<WebElement> elements){
		WebElement ele = null;
		for(int i=0; i<elements.size(); i++){
			ele = elements.get(i);
			String elementName = ele.getAttribute("value");
			if(fieldType.equalsIgnoreCase("radiobutton")==true && ele.getAttribute("type").equalsIgnoreCase("checkbox")==true){
				break;
			}
			if(ele.isEnabled()==true){
				System.out.println("Enable status of: "+fieldType+"-"+elementName.toUpperCase()+" is true");
			}else{
				System.out.println("Enable status of: "+fieldType+"-"+elementName.toUpperCase()+" is false");
			}
		}
	}
*/	 
 	
	/////////////////////Need to understand this/////////////////////////////////////
/* static void checkDisplayStatus(String fieldType, List<WebElement> elements){
		WebElement ele = null;
		for(int i=0; i<elements.size(); i++){
			ele = elements.get(i);
			String elementName = ele.getAttribute("value");
			if(fieldType.equalsIgnoreCase("radiobutton")==true && ele.getAttribute("type").equalsIgnoreCase("checkbox")==true){
				break;
			}
			if(ele.isDisplayed()==true){
				System.out.println("Dislay status of: "+fieldType+"-"+elementName.toUpperCase()+" is true");
			}else{
				System.out.println("Dislay status of: "+fieldType+"-"+elementName.toUpperCase()+" is false");
			}
		}
 	}
*/	 
 
    /***************************************Browser navigation forward backward ************************************/
/*	public static void browserNavFwdBkwd(String Command){
		if(Command.equalsIgnoreCase("backward")){
			driverObj.navigate().back();
		}else {
			driverObj.navigate().forward();
		}
	}
	*/
			 
///********************************************************Get Element/Locate Element**************************************************************\\
/*	public static WebElement getElement(String locatorType, String value){
		WebElement ele = null;
		if(locatorType.equalsIgnoreCase("XPATH")){
			 
				setXpath( value);
				ele = driverObj.findElement(By.xpath(getXpath()));	
		}
		else if(locatorType.equalsIgnoreCase("ID")){
				setId(value);
				ele = driverObj.findElement(By.id(getId()));
 			setId(value);
			ele = driverObj.findElement(By.id(getId()));
		}else if(locatorType.equalsIgnoreCase("NAME")){
			ele = driverObj.findElement(By.name(getName()));
		}else{
			System.out.println("Please type correct locator type out of: XPATH or ID or NAME");
		}
		return ele;
	}
	
*/
    											/********************Resize Element***************************/
 /*   public static void resizeElement(WebElement we, int xoffSet, int yOffset ){
	 	 
					Actions builder = new Actions(driverObj);
					builder.clickAndHold(we).moveByOffset(xoffSet, yOffset).release().build().perform();
		 
	}
*/
    											/********************DropElement***************************/
    
/*	public static void dropElement(WebElement sourceElement, WebElement destElement, String locatorType, String locatorValue, String expectedText ){
		 
		Actions builder = new Actions(driverObj);
		String initalTextDestElmnt= UI_GenericMethods1.getElement(locatorType, locatorValue).getText().toString();
		System.out.println(initalTextDestElmnt);  
		builder.dragAndDrop(sourceElement, destElement).build().perform();
		String finalTextDestElmnt = UI_GenericMethods1.getElement(locatorType, locatorValue).getText().toString();
		System.out.println(finalTextDestElmnt);
		System.out.println("22222222");
		if(finalTextDestElmnt.equalsIgnoreCase(expectedText)){
			System.out.println("Pass. Actual Text after drop operation: "+finalTextDestElmnt+" - is equal to expected text:  "+expectedText);
		}else{
			System.out.println("Fail. Actual Text after drop operation: "+finalTextDestElmnt+ " - is not equal to expected text:  "+expectedText);
		}
		
	}
*/
												/********************DragElement***************************/
/*	public static void dragElement(WebElement element, int xOffset, int yOffset ){
		Actions builder = new Actions(driverObj);
		builder.dragAndDropBy(element, xOffset, yOffset).build().perform();
	}

	public static List <WebElement> getElements(String locatorType, String value){
		List <WebElement> ele = null;
		//WebElement  element = null;
		String ckbBoxName = null;
		//if(isList==true || Autocomplete == true){
				if(locatorType.equalsIgnoreCase("XPATH")){
					setXpath(value);
					ele = driverObj.findElements(By.xpath(getXpath()));
				}else if(locatorType.equalsIgnoreCase("ID")){
					setId(value);
					ele = driverObj.findElements(By.id(getId()));
				}else if(locatorType.equalsIgnoreCase("NAME")){
					ele = driverObj.findElements(By.name(getName()));
				}else{
					System.out.println("Please type correct locator type out of: XPATH or ID or NAME");
				}
//		}else{
	//		System.out.println("Please mention isList/Autocomplete or not");
	//	}
	//	int eleCount = ele.size();
	//	for(int i= 0; i<eleCount;i++){
	//		element = ele.get(i);
	//		ckbBoxName = element.getAttribute("value");
	//	}
		return ele;
	}
*/
	 

	public static String getXpath(){
		return ele_xpathh;
	}
	public static void setXpath(String xp){
		ele_xpathh = xp;
	}
	public static String getId(){
		return ele_idd;
	}
 	public static void setId(String  idd){
 		ele_idd = idd;
	}
	public static String getName(){
		return ele_Name;
	}
	public static void setName(String Name){
		ele_Name = Name;
	}
	public static String getClassName(){
		return ele_className;
	}
	public static void setClassName(String className){
		ele_className = className;
	}
	public static String getLinkText(){
		return ele_linkText;
	}
	public static void setLinkText(String linkText){
		ele_linkText = linkText;
	}

	

	

	
	
	
	//To be used directly in the test class
	/********************ValidateNavigatedURL***************************/
   /*
    public static void validateNavigatedURL(String url, String ElementName, String expURL){
    
    	if(url.equalsIgnoreCase(expURL)){
    		System.out.println(ElementName+" is directed to correct page having URL "+url);
    	}else{
    		System.out.println(ElementName+" is directed to wrong page with URL"+url);
    	}  
    }
    */
	/*  											//Very complicated and lengthy to use 
	 * 
	 * public static void clickElement(String fieldType, List<WebElement> elements, String command, String eleVal){
		WebElement ele = null;
		System.out.println(elements.size());
		for(int i=0; i<elements.size(); i++){
			ele = elements.get(i);
			//String elementName = ele.getAttribute("value");
			//if(elementName.isEmpty()==true){
			String elementName = elements.get(i).getText();
			//}
			if(fieldType.equalsIgnoreCase("radiobutton")==true && ele.getAttribute("type").equalsIgnoreCase("checkbox")==true){
				break;
			}
			if(command.equalsIgnoreCase("SelectAll")){
				ele.click();
				System.out.println(fieldType+" - "+elementName.toUpperCase()+" has been clicked");
			}else if(command.equalsIgnoreCase("ByName")){
				if(elementName.equalsIgnoreCase(eleVal)){
					ele.click();
					System.out.println(fieldType+" - "+elementName.toUpperCase()+" has been clicked");
				}else{
					//write any comdition
				}
			}else if(command.equalsIgnoreCase("ByIndex")){
				int intVal = Integer.parseInt(eleVal);
				if((i == intVal) == true){
				ele.click();
				System.out.println(fieldType+" - "+elementName.toUpperCase()+" has been clicked");
				}
			}else{
				System.out.println("Please input corrrect value");
			}
		}
 	}
 	*/
 
}

