package vtigercrm;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commonutil.BaseClass;
import commonutil.ExcelUtil;
import commonutil.JavaUtil;
import commonutil.PropertyFileUtil;
import commonutil.WebDriverUtil;

public class OrganizationTestNG extends BaseClass {
	
	
	// import PropertyFileUtil class
	PropertyFileUtil putil = new PropertyFileUtil();

	// Creating an object of WebDriverUtil class
	WebDriverUtil wutil = new WebDriverUtil();

	// Create an object of ExcelUtil
	ExcelUtil eutil = new ExcelUtil();

	// Create an object of JavaUtil
	JavaUtil jutil = new JavaUtil();

	@Test
	public void organizationTest() throws IOException, NullPointerException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		/*
		 * WebDriver driver = new ChromeDriver();
		 * 
		 * // To maximize the window wutil.maximize(driver);
		 * 
		 * // To apply wait for findlement() wutil.implicitlyWait(driver);
		 */

		// To Reading the data from property file
		/*
		 * String url = putil.getDataFromPropertyFile("Url");
		 * 
		 * String username = putil.getDataFromPropertyFile("Username");
		 * 
		 * String password = putil.getDataFromPropertyFile("Password");
		 */ // copy it from propety file

		// To read data from Excel File
		String ORGNAME = eutil.getDataFromExcel("Organizations", 0, 1);

		String GROUP = eutil.getDataFromExcel("Organizations", 1, 1);

		// To launch the application
		/*
		 * driver.get(url);
		 * 
		 * driver.findElement(By.name("user_name")).sendKeys(username);
		 * 
		 * driver.findElement(By.name("user_password")).sendKeys(password);
		 * 
		 * driver.findElement(By.id("submitButton")).click();
		 */

		// Click on Organizations
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();

		// To click on '+' symbol
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// To Enter the organization name
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME + "  " + jutil.getRandomNumber());

		// In Assigned to click on radio button - group
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// In the dropdown select support group
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));

		// Call the handledDropdown()
		wutil.handledDropdown(dropdown, "Support Group");

		// Click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();

		// To take Screenshot of organization
		wutil.screenshot(driver, "Organizations");

		Thread.sleep(4000);

		// Mousehover on img
		/*
		 * WebElement image =
		 * driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']")
		 * );
		 * 
		 * 
		 * wutil.mouseHover(driver, image);
		 * 
		 * // To click on signout button
		 * driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		 */

	}
}
