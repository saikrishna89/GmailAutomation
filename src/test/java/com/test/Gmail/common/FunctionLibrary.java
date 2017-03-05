package com.test.Gmail.common;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.Gmail.Utils.ExcelUtils;

public class FunctionLibrary {

	public WebDriver driver;

	ExcelUtils excelUtils = new ExcelUtils();

	public void setUp(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\Gmail\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://www.gmail.com");
		}
	}

	public void clickElementById(String Id) {
		if (driver != null) {
			driver.findElement(By.id(Id)).click();;
		}
	}

	public void clickElementByLinkText(String LinkText) {
		if (driver != null) {
			driver.findElement(By.linkText(LinkText)).click();;
		}
	}

	public void sendKeysById(String Id, String keys) {
		driver.findElement(By.id(Id)).sendKeys(keys);
	}
	
	public String validatePageByURL(){
		String currentURL = null;
		if(driver != null){
			try{
				currentURL = driver.getCurrentUrl();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return currentURL;
	}
	
	public void clearTextFieldById(String Id){
		if(driver != null){
			try{
				driver.findElement(By.id(Id)).clear();
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
	}
	
	public String fnVerifyDialogBox(){
		String actualErrorMessage = null;
		if(driver != null){
		try{
			actualErrorMessage = driver.findElement(By.id("errormsg_0_Passwd")).getText();
		}catch(Exception E){
			System.out.println(E.getMessage());
		}
	}
	return 	actualErrorMessage;
	}	
	
	public void signOut(){
		if(driver != null){
		try{
			driver.findElement(By.xpath("//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
			driver.findElement(By.id("gb_71")).click();
		}catch(Exception E){
			System.out.println(E.getMessage());
		}
	}
	}
	
	public boolean isChecked(String Id){
		boolean bflag = false;
		if(driver != null){
			try{
				bflag = driver.findElement(By.id(Id)).isSelected();
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
		return bflag;
	}
	
	public void checkUncheckBox(String Id){
		if(driver != null){
			try{
				driver.findElement(By.id(Id)).click();
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
	}
	
	public void selectDropDown(WebElement Ele, String option){
		if(driver != null){
			try{
				Select sel = new Select(Ele);
				List<WebElement> dropDownSelections = sel.getOptions();
				Iterator<WebElement> ite = dropDownSelections.iterator();
				while(ite.hasNext()){
					if(ite.next().toString().equalsIgnoreCase(option)){
						sel.selectByValue(option);
					}
				}
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
	}
	
	public WebElement findElementById(String Id){
		WebElement Ele = null;
		if(driver != null){
			try{
				Ele = driver.findElement(By.id(Id));
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
		return Ele;
	}
	
	public String getText(String xpath){
		String text = null;
		if(driver != null){
			try{
				text = driver.findElement(By.xpath(xpath)).getText();
			}catch(Exception E){
				System.out.println(E.getMessage());
			}
		}
		return text;
	}
}
