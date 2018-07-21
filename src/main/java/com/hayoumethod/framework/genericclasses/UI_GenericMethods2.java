package com.hayoumethod.framework.genericclasses;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hayoumethod.framework.pageobject.homepage.Homepage;

//import com.hayoumethod.framework.pageobject.account.Login;
//import com.hayoumethod.framework.pageobject.homepage.Homepage;



	public class UI_GenericMethods2 extends XL_GenericMethods {
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
		
	/********************Launch Browser**************************/
	public void gm_LaunchBrowser(String BrowserName) throws IOException{
		
			if(BrowserName.equalsIgnoreCase("IE")==true){
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer_64.exe");
				driverObj= new InternetExplorerDriver();
				gm_WriteToLog("IE has been launched", "I");
				 
				
			}else if(BrowserName.equalsIgnoreCase("CH")==true){
				System.setProperty("webdriver.chrome.driver", "E:\\QA\\Selenium\\WorkspaceMars\\HAYOUMETHOD\\Drivers\\Drivers_Sel3.4\\chromedriver.exe");
				driverObj= new ChromeDriver();
				gm_WriteToLog("Chrome has been launched", "I");
	 
			  
			}else if(BrowserName.equalsIgnoreCase("FF")==true){
				System.setProperty("webdriver.gecko.driver", "E:\\QA\\AutomationTools\\Selenium\\WorkspaceMars\\Project_HayouMethod2\\Drivers\\GeckoDriver\\64Bit\\v16\\geckodriver.exe");
				driverObj= new FirefoxDriver();
				gm_WriteToLog("Firefox has been launched", "I");
		 
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
		WebElement allowCookie_btn = driverObj.findElement(By.xpath("//button[@id='btn-cookie-allow']"));
		
		System.out.println("Before - Allow cookie - Click");
		System.out.println("AB4");
		Thread.sleep(3000);
		gm_WaitVIsibility_Java(allowCookie_btn, 60);
	//	allowCookie_btn.click();
		System.out.println("After - Allow cookie - Click");
	//	Login loginObj=PageFactory.initElements(driverObj, Login.class);
		Homepage homeObj=PageFactory.initElements(driverObj, Homepage.class);
		//Navigation NavObject=PageFactory.initElements(driverObj, Navigation.class);
	  return homeObj;
	}
	
	/********************Input Method**************************/
	
	
	public void gm_Input(WebElement we, String inputValue,String WEName, int timeout_Seconds) throws IOException{
		try{
			we.clear();
			we.sendKeys(inputValue);
			gm_WriteToLog(inputValue+ "has been entered in "+WEName, "I");
		}catch(NoSuchElementException ne){
			gm_WaitVIsibility_Java(we, timeout_Seconds);	
			
			
		}catch(ElementNotVisibleException nv){
			gm_WriteToLog(WEName+" is not visible", "E");
		}catch(StaleElementReferenceException se){
			
		}
			
	}
	
	public void gm_XLInput1(WebElement we, String ColumnName_Key,String WEName, int timeout_Seconds) throws IOException{
		try{
			
			String InputValue=(String) testData_HM.get(ColumnName_Key);
			String TagName=we.getTagName();
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
				   
				   
			}else{
				we.clear();
				we.sendKeys(InputValue);
			}
		
			gm_WriteToLog(InputValue+ "has been entered in "+WEName, "I");
		}catch(NoSuchElementException ne){
			gm_WaitVIsibility_Java(we, timeout_Seconds);	
			
			
		}catch(ElementNotVisibleException nv){
			gm_WriteToLog(WEName+" is not visible", "E");
		}catch(StaleElementReferenceException se){
			
		}
			
	}
	
	/********************Wait Visibility**************************/
	public void gm_WaitVIsibility(WebElement we, int timeOut_Sec){
		WebDriverWait wtObj=new WebDriverWait(driverObj, timeOut_Sec);	
		wtObj.until(ExpectedConditions.visibilityOf(we));
		
	}
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
	
	
	/********************Wait Clickable**************************/
	public void gm_WaitClickable(WebElement we, int timeOut_Sec){
		WebDriverWait wtObj=new WebDriverWait(driverObj, timeOut_Sec);	
		wtObj.until(ExpectedConditions.elementToBeClickable(we));
		
	}
	

	/********************Is Visible**************************/

	public boolean gm_isVisible(WebElement we){
		
		boolean visibility=we.isDisplayed();
		return visibility;
	}
	
	/********************Is Enabled t**************************/

	public boolean gm_isEnabled(WebElement we){
	
	boolean visibility=we.isEnabled();
	return visibility;
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
	/********************ValidateText**************************/
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
	public void gm_Validate(String ActualText, String exp, String ElementName) throws InvalidFormatException, IOException, InterruptedException{
		System.out.println("In gm_Validate method");
		
		String ImagePath  = null; 
		if(ActualText.equalsIgnoreCase(exp)){
			ImagePath="Reports/Images/"+ElementName+"_"+fn_GetTimeStamp()+".png";
			ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			String[] ResArray=gm_GetResultStepArray(ElementName, exp, ActualText, "Passed",ImagePath);
			xl_WriteResultToExcel(ResArray);
		}else{
			 ImagePath="Reports/Images/"+ElementName+"_"+fn_GetTimeStamp()+".png";
			ImagePath=ImagePath.split("/", 2)[1];
			gm_TakeSnapshot(driverObj, ImagePath);
			
			String[] ResArray=gm_GetResultStepArray(ElementName, exp, ActualText, "Failed", ImagePath);
			xl_WriteResultToExcel(ResArray);
		} 
	}
	
	
	/********************ValidateState**************************/
	public  void gm_ValidateState(WebElement we,String ElementName ,String exp) throws InvalidFormatException, IOException, InterruptedException{
		Boolean Actualstatus=we.isEnabled();
		String ActualValue=Actualstatus.toString();
		gm_Validate(ActualValue, exp, ElementName);
		
	}
	public  String[] gm_GetResultStepArray(String ElementName,String exp,String ActualText,String Status, String SnapshotPath){
	      Throwable t=new Throwable();
	      StackTraceElement[] arr_Ste=t.getStackTrace();
	      StackTraceElement ste=arr_Ste[3];
	      String MethodName=ste.getMethodName();
	      String metname1 = MethodName.substring(0,1).toUpperCase();
	      String metname2 = MethodName.substring(1).toLowerCase();
	      MethodName = metname1.concat(metname2);
	      String ClassName=ste.getClassName();
	      String[]ArrClsName=ClassName.split("\\.");
	      String SubModuleName=ArrClsName[ArrClsName.length-1];
	      String ModuleName=ArrClsName[ArrClsName.length-2];
	      String modname1 = ModuleName.substring(0,1).toUpperCase();
	      String modname2 = ModuleName.substring(1).toLowerCase();
	      ModuleName = modname1.concat(modname2);
	     
	      String[] ResultArray={ModuleName, SubModuleName, MethodName,ElementName, exp, ActualText, Status, SnapshotPath};
 
	      return ResultArray;
	}
	
	public  String[] gm_GetResultStepArray1(String ElementName,String exp,String ActualText,String Status, String SnapshotPath){
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
    private static final Logger log = Logger.getLogger(UI_GenericMethods2.class.getName());
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
    /********************ValidateNavigatedURL***************************/
   
    public static void validateNavigatedURL(String url, String ElementName, String expURL){
    
    	if(url.equalsIgnoreCase(expURL)){
    		System.out.println(ElementName+" is directed to correct page having URL "+url);
    	}else{
    		System.out.println(ElementName+" is directed to wrong page with URL"+url);
    	}  
    }
    /********************Resize Element***************************/
    public static void resizeElement(WebElement we, int xoffSet, int yOffset ){
		//try{
		//		if (elementToResize.isDisplayed()){
					Actions builder = new Actions(driverObj);
					builder.clickAndHold(we).moveByOffset(xoffSet, yOffset).release().build().perform();
		//		}else{
		//			System.out.println("Element was not displayed to drag");
		//		}
		//} catch (StaleElementReferenceException e) {
		//	System.out.println("Element with " + elementToResize + "is not attached to the page document "	+ e.getStackTrace());
		//} catch (NoSuchElementException e) {
		//	System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
		//} catch (Exception e) {
		//	System.out.println("Unable to resize" + elementToResize + " - "	+ e.getStackTrace());
		//}
	}

	public static void dropElement(WebElement sourceElement, WebElement destElement, String locatorType, String locatorValue, String expectedText ){
		 
		Actions builder = new Actions(driverObj);
		String initalTextDestElmnt= UI_GenericMethods2.getElement(locatorType, locatorValue).getText().toString();
		System.out.println(initalTextDestElmnt);  
		builder.dragAndDrop(sourceElement, destElement).build().perform();
		String finalTextDestElmnt = UI_GenericMethods2.getElement(locatorType, locatorValue).getText().toString();
		System.out.println("11111111");
		System.out.println(finalTextDestElmnt);
		System.out.println("22222222");
		if(finalTextDestElmnt.equalsIgnoreCase(expectedText)){
			System.out.println("Pass. Actual Text after drop operation: "+finalTextDestElmnt+" - is equal to expected text:  "+expectedText);
		}else{
			System.out.println("Fail. Actual Text after drop operation: "+finalTextDestElmnt+ " - is not equal to expected text:  "+expectedText);
		}
		
	}

	
	public static void dragElement(WebElement element, int xOffset, int yOffset ){
		Actions builder = new Actions(driverObj);
		builder.dragAndDropBy(element, xOffset, yOffset).build().perform();
	}

	public static void selectDropdown(String fieldType, WebElement dropdwnElement, String command, String value){
		
		Select slctDropdown = new Select(dropdwnElement);
		List <WebElement> drpdwnOptions = slctDropdown.getOptions();
		int optionCount = drpdwnOptions.size();
		/*//////////////////Method1
		  for(int i=0; i<optionCount ; i++){
			slctDropdown.selectByIndex(i);
			System.out.println(fieldType+" option "+i+" is: "+slctDropdown.getFirstSelectedOption().getText());
		}
		 ///////////////////Method2
		 
		*/
		if(command.equalsIgnoreCase("SelectAll")){
			for(int i=0; i<optionCount; i++){
				WebElement  drpDwnEle = drpdwnOptions.get(i);
				System.out.println("Selected "+fieldType+" option "+i+"  is: "+drpDwnEle.getText());
			}
		}else if(command.equalsIgnoreCase("selectByVisibleText")){
				slctDropdown.selectByVisibleText(value);
				System.out.println("Selected "+fieldType+" option is: "+slctDropdown.getFirstSelectedOption().getText());
		}else if(command.equalsIgnoreCase("selectByValue")){
				slctDropdown.selectByValue(value);
				System.out.println("Selected "+fieldType+" option is: "+slctDropdown.getFirstSelectedOption().getText());
		}else if(command.equalsIgnoreCase("selectById")){
				int val = Integer.parseInt(value);
				slctDropdown.selectByIndex(val);
				System.out.println("Selected "+fieldType+" option is: "+slctDropdown.getFirstSelectedOption().getText());
		 }else{
		    	System.out.println("Please enter correct Command");
		 }
	}
	public static void clickElement(String fieldType, List<WebElement> elements, String command, String eleVal){
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
 	public static void selectElement(String fieldType, List<WebElement> elements, String command, String eleVal){
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
 	public static void checkDisplayStatus(String fieldType, List<WebElement> elements){
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
 	public static void checkEnableStatus(String fieldType, List<WebElement> elements){
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
	public static void checkSelectStatus(String fieldType, List<WebElement> elements){
		WebElement ele = null;
		for(int i=0; i<elements.size(); i++){
			ele = elements.get(i);
			String elementName = ele.getAttribute("value");
			if(fieldType.equalsIgnoreCase("radiobutton")==true && ele.getAttribute("type").equalsIgnoreCase("checkbox")==true){
				break;
			}
			if(ele.isSelected()==true){
				System.out.println("Select status of: "+fieldType+"-"+elementName.toUpperCase()+" is true");
			}else{
				System.out.println("Select status of: "+fieldType+"-"+elementName.toUpperCase()+" is false");
			}
		}
 	}

	public static List <WebElement> getElements(String locatorType1, String value1){
		List <WebElement> ele = null;
		//WebElement  element = null;
		String ckbBoxName = null;
		//if(isList==true || Autotcomplete == true){
				if(locatorType1.equalsIgnoreCase("XPATH")){
					setXpath(value1);
					ele = driverObj.findElements(By.xpath(getXpath()));
				}else if(locatorType1.equalsIgnoreCase("ID")){
					setId(value1);
					ele = driverObj.findElements(By.id(getId()));
				}else if(locatorType1.equalsIgnoreCase("NAME")){
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
////////////////////////////////Text Field///////////////////////////////////////
	public static String inputValuesTextField(WebElement element, String inputVal){
		element.clear();
		element.sendKeys(inputVal);
		//String inputtxt = element.getText();
		//System.out.println(inputtxt);
		return inputVal;
	}
	
	public static void checkDisplayStatus(String name, WebElement elementName){
		if(elementName.isDisplayed()){
			System.out.println("Display status of: "+name+": true");
		}else{
			System.out.println("Display status of: "+name+": false");
		}
	}
	public static void checkEnabledStatus(String name, WebElement elementName){
		if(elementName.isEnabled()){
			System.out.println("Enable status of: "+name+": true");
		}else{
			System.out.println("Enable status of: "+name+": false");
		}
	}
	public static void checkSelectedStatus(String name, WebElement elementName){
		if(elementName.isSelected()){
			System.out.println("Display status of: "+name+": true");
		}else{
			System.out.println("Display status of: "+name+": false");
		}
	}
////////////////////////////////Text Field///////////////////////////////////////
	public static void browserNavigation(String Command, String URL2){
		if(Command.equalsIgnoreCase("backward")){
			driverObj.navigate().back();
		}else if(Command.equalsIgnoreCase("forward")){
			driverObj.navigate().forward();
		}else{
			driverObj.navigate().to(URL2);
		}
	}
	public static void launchBrowser(String URL){
		driverObj.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		driverObj.get(URL);
	}
	public static WebElement getElement(String locatorType, String value){
		WebElement ele = null;
		if(locatorType.equalsIgnoreCase("XPATH")){
			setXpath(value);
			ele = driverObj.findElement(By.xpath(getXpath()));
		}else if(locatorType.equalsIgnoreCase("ID")){
			setId(value);
			ele = driverObj.findElement(By.id(getId()));
		}else if(locatorType.equalsIgnoreCase("NAME")){
			ele = driverObj.findElement(By.name(getName()));
		}else{
			System.out.println("Please type correct locator type out of: XPATH or ID or NAME");
		}
		return ele;
	}
	
	public static void LaunchURL(String profileName, String URL, String browserName){
		//Create Enum for Browsers
		if(browserName.equalsIgnoreCase("Firefox")==true){
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("profileToolsQA");
		WebDriver driverObj = new FirefoxDriver();
		driverObj.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		driverObj.get(URL);
		}else if(browserName.equalsIgnoreCase("Chrome")==true){
			System.out.println("//Need to write code here for Chrome");
		}else if(browserName.equalsIgnoreCase("IE11")==true){
			System.out.println("//Need to write code here for IE11");
		}else if(browserName.equalsIgnoreCase("Safari")==true){
			System.out.println("//Need to write code here for Safari");
		}else if(browserName.equalsIgnoreCase("Opera")==true){
			System.out.println("//Need to write code here for Opera");
		}else{
			System.out.println("Please enter correct browser name");
		}
	}

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

}

