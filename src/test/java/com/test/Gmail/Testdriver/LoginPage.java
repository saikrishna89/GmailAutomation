package com.test.Gmail.Testdriver;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.Gmail.ObjectRepository.ObjectRepository;
import com.test.Gmail.Utils.ExcelUtils;

public class LoginPage extends TestDriver{
	
	ObjectRepository OR = new ObjectRepository();
	ExcelUtils excelUtil = new ExcelUtils();
	
	SoftAssert asserting = new SoftAssert();

	@BeforeSuite()
	public void beforeSuite(){
		setUp("chrome");
	}
	
	@AfterSuite()
	public void afterSuite(){
		if(driver != null){
			driver.quit();
		}
	}
	
	@Test
	@SuppressWarnings("static-access")
	public void TC0001(){
		log.info("Executing TestCase TC0001");
		try{
			List<String> values = excelUtil.getCellData("TC0001");
			String uname = values.get(0);
			String password = values.get(1);	
			clearTextFieldById(OR.email_Field_Id);
			sendKeysById(OR.email_Field_Id, uname);
			clickElementById(OR.next_Button_Id);
			sendKeysById(OR.password_Field_Id, password);
			if(isChecked(OR.stay_SignedIn_checkbox_Id)){
				checkUncheckBox(OR.stay_SignedIn_checkbox_Id);
			}
			clickElementById(OR.signIn_Button_Id);
			String actualURL = "https://mail.google.com/mail/u/0/#inbox";
			Assert.assertEquals(actualURL, validatePageByURL());
			System.out.println("Page validated");
			signOut();
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destfile = null;
			FileUtils.copyFile(file, destfile);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@SuppressWarnings("static-access")
	public void TC0002(){
		try{
			List<String> values = excelUtil.getCellData("TC0002");
			String uname = values.get(0);
			String password = values.get(1);
			clearTextFieldById(OR.email_Field_Id);
			sendKeysById(OR.email_Field_Id, uname);
			clickElementById(OR.next_Button_Id);
			sendKeysById(OR.password_Field_Id, password);
			clickElementById(OR.signIn_Button_Id);
			String actualErrorMessage = "Wrong password. Try again.";
			asserting.assertEquals(actualErrorMessage, fnVerifyDialogBox());
			System.out.println("Verified Error Text");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@SuppressWarnings("static-access")
	public void TC0003(){
		try{
			WebElement Ele = findElementById(OR.language_Dropdown_Id);
			selectDropDown(Ele, "Nederlands‬");
			String actualTitle = "Log in om door te gaan naar Gmail";
		//	System.out.println(actualTitle);
			Assert.assertEquals(actualTitle, getText("html/body/div[1]/div[2]/div[1]/h2"));
		//	asserting.assertEquals(actualTitle, getText("html/body/div[1]/div[2]/div[1]/h2"));
			System.out.println("Page converted to Netherlands language");
		}catch(Exception E){
			System.out.println(E.getMessage());
		}
	}
}
