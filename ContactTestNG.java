package vtigercrm;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonutil.BaseClass;
import commonutil.ExcelUtil;
import commonutil.JavaUtil;
import commonutil.PropertyFileUtil;
import commonutil.WebDriverUtil;

public class ContactTestNG extends BaseClass {
	
	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	ExcelUtil eutil = new ExcelUtil();
	JavaUtil jutil = new JavaUtil();

	@Test
	public void contactTest() throws IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();

		// TO maximize the window
		wutil.maximize(driver);

		// To apply wait for find element
		wutil.implicitlyWait(driver);

		String url = putil.getDataFromPropertyFile("Url");

		String username = putil.getDataFromPropertyFile("Username");

		String password = putil.getDataFromPropertyFile("Password");

		// To read data from Excel File
		String FirstName = eutil.getDataFromExcel("Contacts", 0, 1);

		String LastName = eutil.getDataFromExcel("Contacts", 1, 1);

		String Group = eutil.getDataFromExcel("Contacts", 2, 1);

		String Organization = eutil.getDataFromExcel("Contacts", 3, 1);

		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(username);

		driver.findElement(By.name("user_password")).sendKeys(password);

		driver.findElement(By.id("submitButton")).click();

		// To click on Contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// To click on + icon
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();

		// To send the FirstName Name
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(FirstName);

		// To send the LastName
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(LastName);
		
		// To fail the testScript
	/*	WebElement notifyOwner = driver.findElement(By.cssSelector("input[name='notify_owner']"));
		Assert.assertTrue(notifyOwner.isSelected());   */
		
		// OR
		String ActURL = driver.getCurrentUrl();
		String ExpURL = " ";
		Assert.assertEquals(ActURL, ExpURL);
		
		
		// In a group select Team selling
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// In the dropdown select - Team selling
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		wutil.handledDropdown(dropdown, Group);

		// Click on select (+) in organization name text field
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

		// Transfer the driver control from parent window to child window
		Set<String> ids = driver.getWindowHandles();
		System.out.println("No.of Windows : " + ids);

		for (String a : ids) {

			String actualUrl = driver.switchTo().window(a).getCurrentUrl();

			System.out.println(actualUrl);
			String expectedUrl = "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";

			if (actualUrl.contains(expectedUrl)) {
				break;
			}

		}

		// To enter organization name in searchtf
		driver.findElement(By.id("search_txt")).sendKeys(Organization);

		// To click on the search Now button
		driver.findElement(By.cssSelector("input[name='search']")).click();

		// To click on organization name
		driver.findElement(By.xpath("//a[text()='Flipkart  439']")).click();

		// To transfer the control from child window to parent windwow
		wutil.switchWindow(driver,
				"http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");

		// Click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();

		Thread.sleep(4000);

		// Mousehover on img
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		wutil.mouseHover(driver, image);

		// To click on signout button
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}

