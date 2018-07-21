package com.hayoumethod.framework.pageobject.myaccount;


import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hayoumethod.framework.genericclasses.UI_GenericMethods1;
import com.hayoumethod.framework.pageobject.myaccount.addressbook.AddAddress;
import com.hayoumethod.framework.pageobject.myaccount.addressbook.AddressBook;
//import com.hayoumethod.framework.pageobject.myaccount.addressbook.EditAddress;

public class AccountDashboard extends UI_GenericMethods1{

	//LeftNav
	@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li/a")
	private static WebElement leftNavlnks;
	
	//LeftNav Account Dashboard
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[0]/a")
		private static WebElement leftNavAccountDashboardlink;
		
		//LeftNav Account Information
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[2]/a")
		private static WebElement leftNavAccountInformationlink;
		
		//LeftNav Address Book
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[3]/a")
		private static WebElement leftNavAddressBooklink;
		
		//LeftNav Newsletter Subscriptions
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[4]/a")
		private static WebElement leftNavNewsletterSubscriptionslink;
		
		//LeftNav My Orders
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[5]/a")
		private static WebElement leftNavMyOrderslink;
		
		//LeftNav Stored Payment Methods
		@FindBy(how = How.XPATH, using ="//ul[@class='nav items']/li[6]/a")
		private static WebElement leftNavStoredPaymentMethodslink;
		
				

	//PageTitle

	@FindBy(how = How.XPATH, using ="//div[@class='row more-padding']//h1")
	public static WebElement PageTitle;


	//PageSubtitle

	@FindBy(how = How.XPATH, using ="//div[@class='block block-dashboard-info']//strong")
	private static WebElement PageSubtitle;


	//AccountInfo section title
	@FindBy(how = How.XPATH, using ="//div[@class='box box-information']/strong")
	private static WebElement accountInfoTitle;

	//Contact Info EditLink
	@FindBy(how = How.XPATH, using ="//div[@class='box box-information']//a[contains(.,'Edit')]")
	private static WebElement cntctInfoEdtLnk;

	//Contact Info ChangePwd  link
	@FindBy(how = How.XPATH, using ="//div[@class='box box-information']//a[contains(.,'Change Password')]")
	private static WebElement chngPwdlink;
	
	//Newsletter section title
	@FindBy(how = How.XPATH, using ="//div[@class='box box-newsletter']/strong")
	private static WebElement newslttrTitle;

	//Newsletter Edit Link
	@FindBy(how = How.XPATH, using ="//div[@class='box box-newsletter']//a")
	public static WebElement nwslttrEdtLnk;
	
	
	//Address book section title
	@FindBy(how = How.XPATH, using ="//div[@class='block block-dashboard-addresses']//strong")
	private static WebElement addressBookTitle ;


	//Manage AddresLink
	@FindBy(how = How.XPATH, using ="//div[@class='block-title']/a")
	private static WebElement mngAdsLnk;
	
	//Billing Address section title
	@FindBy(how = How.XPATH, using ="//div[@class='box box-billing-address']//span")
	private static WebElement billingAddressTitle;

	//BillingAddrsEditLnk
	@FindBy(how = How.XPATH, using ="//div[@class='box box-billing-address']//a")
	private static WebElement billingAddrsEditLnk;
	
	
	
	//Shipping Address section title
	@FindBy(how = How.XPATH, using ="//div[@class='box box-shipping-address']//span")
	private static WebElement shippingAddressTitle;

	//ShippingAddrsEditLnk
	@FindBy(how = How.XPATH, using ="//div[@class='box box-shipping-address']//a")
	private static WebElement shippingAddrsEditLnk;
	
	//Confirmation Message
	@FindBy(how = How.XPATH, using ="//div[@class='message-success success message']")
	private static WebElement cnfrmtnMsg_txt;
	
	
	
	//MyAccount icon
	@FindBy(how = How.XPATH, using ="//div[@class='userPanelWrap']/a")
	private static WebElement myAccntIcon_hdr;
	
	//Logout icon
	@FindBy(how = How.XPATH, using ="//ul[@class='userPanelNav']/li[6]/a")
	public static WebElement logOutIcon;
	
	
	 
	
	
	public AccountInformation navigateToAccountInformationPage(){
		driverObj.navigate().to("http://www.hayoumethod.com/customer/account/edit/");
		AccountInformation acntInfo = PageFactory.initElements(driverObj, AccountInformation.class);
		return acntInfo;
	}

	
	public AddressBook navigateToAddrsBookPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/customer/address/");
		AddressBook addrsBook = PageFactory.initElements(driverObj, AddressBook.class);
		return addrsBook;
	}
	
	public NewsletterSubscription navigateToNewsLetterSubscriptionPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/newsletter/manage/");
		NewsletterSubscription nsltrSubscrptn = PageFactory.initElements(driverObj, NewsletterSubscription.class);
		return nsltrSubscrptn;
	}

	public MyOrders navigateToMyOrderPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/sales/order/history/");
		MyOrders myorderObj = PageFactory.initElements(driverObj, MyOrders.class);
		return myorderObj;
	}

	public StoredPaymentMethod navigateToStoredPaymentMthdPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/vault/cards/listaction/");
		StoredPaymentMethod strdPmntMthdObj = PageFactory.initElements(driverObj, StoredPaymentMethod.class);
		return strdPmntMthdObj;
	}
	
	public AddAddress navigateToAddAddressPage(){
		driverObj.navigate().to("https://www.hayoumethod.com/customer/address/new/");
		AddAddress addAddressPg_Obj = PageFactory.initElements(driverObj, AddAddress.class);
		return addAddressPg_Obj;

	}
	
	//public EditAddress navigateToEditAddressPage(){
	//	driverObj.navigate().to("https://www.hayoumethod.com/customer/address/new/");
	//	EditAddress edtAddressPg_Obj = PageFactory.initElements(driverObj, EditAddress.class);
	//	return edtAddressPg_Obj;
	//}
 
	public String validateSectionTitles(WebElement element) throws InvalidFormatException, IOException, InterruptedException{
		System.out.println("In validateSectionTitles method");
		Thread.sleep(20000);
		//gm_WaitVIsibility(element, 30);
		System.out.println("wait over");
		
		String actualSectionName = element.getText();
		return actualSectionName;

	}
	
	public String validateDashboardLinks(WebElement element) throws Exception{
		System.out.println("In validateDashboardLinks method");
		 	Thread.sleep(20000);
			//gm_WaitVIsibility(element, 30);
			System.out.println("wait over");
			try{
				System.out.println("In try block");
				System.out.println(element.isDisplayed());
				element.click();
				
			}catch(Exception e){
				System.out.println("In Catch block");
				System.out.println(e.getMessage());
				e.printStackTrace();
				gm_WaitVIsibility(element, 30);
		}
			String actURL = driverObj.getCurrentUrl();
			return actURL;
	}
	public String validateLeftNavLinks(WebElement element) throws InterruptedException{
		System.out.println("In validateLeftNavLinks method ");
		//gm_WaitVIsibility(element, 30);
		Thread.sleep(20000);
		 element.click();
		 String actualURL = driverObj.getCurrentUrl();
		 System.out.println("URL Taken");
		 return actualURL;
	}
	
	/*public void validateLeftNavLinks1(String linkName){
		List <WebElement> leftnavLnkColln = driverObj.findElements(By.xpath("//ul[@class='nav items']/li"));
		int leftnavnkCount = leftnavLnkColln.size();
		System.out.println(leftnavnkCount);
		Actions actObj = new Actions(driverObj);
		for(int i=0; i<leftnavnkCount; i++){
			 leftnavLnkColln = driverObj.findElements(By.xpath("//ul[@class='nav items']/li"));
			 WebElement leftnavLnk = leftnavLnkColln.get(i);
			 System.out.println(leftnavLnk.getText());
			 actObj.click(leftnavLnk).build().perform();
			 if(linkName.equalsIgnoreCase("Account Dashboard")){
				 Web
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("My Account"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/customer/account/"));
			 } else if(i==1){
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("Account Information"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/customer/account/edit/"));
			 } else if(i==2){
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("Address Book"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/customer/address/"));
			 } else if(i==3){
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("Newsletter Subscription"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/newsletter/manage/"));
			 } else if(i==4){
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("My Orders"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/sales/order/history/"));
			 } else if(i==5){
				 System.out.println(driverObj.getTitle().equalsIgnoreCase("Stored Payment Methods"));
				 System.out.println(driverObj.getCurrentUrl().contains("https://www.hayoumethod.com/vault/cards/listaction/"));
			 }
			 
			 
		}
		
		
		
		
		
	}
*/
 
	public static void setCntctInfoEdtLnk(WebElement cntctInfoEdtLnk) {
		cntctInfoEdtLnk = cntctInfoEdtLnk;
	}


	public static WebElement getCntctInfoEdtLnk() {
		return cntctInfoEdtLnk;
	}


	public static void setChngPwdlink(WebElement chngPwdlink) {
		chngPwdlink = chngPwdlink;
	}


	public static WebElement getChngPwdlink() {
		return chngPwdlink;
	}


	public static void setNwslttrEdtLnk(WebElement nwslttrEdtLnk) {
		nwslttrEdtLnk = nwslttrEdtLnk;
	}


	public static WebElement getNwslttrEdtLnk() {
		return nwslttrEdtLnk;
	}


	public static void setMngAdsLnk(WebElement mngAdsLnk) {
		mngAdsLnk = mngAdsLnk;
	}


	public static WebElement getMngAdsLnk() {
		return mngAdsLnk;
	}


	public static void setBillingAddrsEditLnk(WebElement billingAddrsEditLnk) {
		billingAddrsEditLnk = billingAddrsEditLnk;
	}


	public static WebElement getBillingAddrsEditLnk() {
		return billingAddrsEditLnk;
	}


	public static void setShippingAddrsEditLnk(WebElement shippingAddrsEditLnk) {
		shippingAddrsEditLnk = shippingAddrsEditLnk;
	}


	public static WebElement getShippingAddrsEditLnk() {
		return shippingAddrsEditLnk;
	}


	public static void setCnfrmtnMsg_txt(WebElement cnfrmtnMsg_txt) {
		AccountDashboard.cnfrmtnMsg_txt = cnfrmtnMsg_txt;
	}


	public static WebElement getCnfrmtnMsg_txt() {
		return cnfrmtnMsg_txt;
	}


	public static WebElement getDashboardPageTitle() {
		return PageTitle;
	}


	public static void setDashboardPageTitle(WebElement pageTitle) {
		PageTitle = pageTitle;
	}


	public static WebElement getPageSubtitle() {
		return PageSubtitle;
	}


	public static void setPageSubtitle(WebElement pageSubtitle) {
		PageSubtitle = pageSubtitle;
	}


	public static WebElement getNewslttrTitle() {
		return newslttrTitle;
	}


	public static void setNewslttrTitle(WebElement newslttrTitle) {
		AccountDashboard.newslttrTitle = newslttrTitle;
	}


	public static WebElement getAddressBookTitle() {
		return addressBookTitle;
	}


	public static void setAddressBookTitle(WebElement addressBookTitle) {
		AccountDashboard.addressBookTitle = addressBookTitle;
	}


	public static WebElement getBillingAddressTitle() {
		return billingAddressTitle;
	}


	public static void setBillingAddressTitle(WebElement billingAddressTitle) {
		AccountDashboard.billingAddressTitle = billingAddressTitle;
	}


	public static WebElement getAccountInfoTitle() {
		return accountInfoTitle;
	}


	public static void setAccountInfoTitle(WebElement accountInfoTitle) {
		AccountDashboard.accountInfoTitle = accountInfoTitle;
	}


	public static WebElement getLeftNavAccountInformationlink() {
		return leftNavAccountInformationlink;
	}


	public static void setLeftNavAccountInformationlink(WebElement leftNavAccountInformationlink) {
		AccountDashboard.leftNavAccountInformationlink = leftNavAccountInformationlink;
	}


	public static WebElement getLeftNavAddressBooklink() {
		return leftNavAddressBooklink;
	}


	public static void setLeftNavAddressBooklink(WebElement leftNavAddressBooklink) {
		AccountDashboard.leftNavAddressBooklink = leftNavAddressBooklink;
	}


	public static WebElement getLeftNavNewsletterSubscriptionslink() {
		return leftNavNewsletterSubscriptionslink;
	}


	public static void setLeftNavNewsletterSubscriptionslink(WebElement leftNavNewsletterSubscriptionslink) {
		AccountDashboard.leftNavNewsletterSubscriptionslink = leftNavNewsletterSubscriptionslink;
	}


	public static WebElement getLeftNavMyOrderslink() {
		return leftNavMyOrderslink;
	}


	public static void setLeftNavMyOrderslink(WebElement leftNavMyOrderslink) {
		AccountDashboard.leftNavMyOrderslink = leftNavMyOrderslink;
	}


	public static WebElement getLeftNavStoredPaymentMethodslink() {
		return leftNavStoredPaymentMethodslink;
	}


	public static void setLeftNavStoredPaymentMethodslink(WebElement leftNavStoredPaymentMethodslink) {
		AccountDashboard.leftNavStoredPaymentMethodslink = leftNavStoredPaymentMethodslink;
	}


	public static WebElement getLeftNavAccountDashboardlink() {
		return leftNavAccountDashboardlink;
	}


	public static void setLeftNavAccountDashboardlink(WebElement leftNavAccountDashboardlink) {
		AccountDashboard.leftNavAccountDashboardlink = leftNavAccountDashboardlink;
	}
	
}
