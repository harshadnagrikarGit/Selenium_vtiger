package vtigercrm;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commonutil.ExcelUtil;
import commonutil.JavaUtil;
import commonutil.PropertyFileUtil;
import commonutil.WebDriverUtil;

public class Leads {

	WebDriverUtil wutil = new WebDriverUtil();
	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	JavaUtil jutil = new JavaUtil();

	@Test
	public void leadsTest() throws IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();

		wutil.maximize(driver);
		wutil.implicitlyWait(driver);

		String url = putil.getDataFromPropertyFile("Url");
		String username = putil.getDataFromPropertyFile("Username");
		String password = putil.getDataFromPropertyFile("Password");

		// To read the data from Excel File
		String FirstName = eutil.getDataFromExcel("Contacts", 0, 1);
		String LastName = eutil.getDataFromExcel("Contacts", 1, 1);
		String Group = eutil.getDataFromExcel("Contacts", 2, 1);
		String Organization = eutil.getDataFromExcel("Contacts", 3, 1);

		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);

		driver.findElement(By.name("user_password")).sendKeys(password);

		driver.findElement(By.id("submitButton")).click();

		// Click on Leads
		driver.findElement(By.xpath("//a[text()='Leads']")).click();

		// Click on create Lead (+)
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();

		// To send the FirstName Name
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(FirstName);

		// To send the LastName
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(LastName);

		// To Enter the company Name
		driver.findElement(By.cssSelector("input[name='company']")).sendKeys(Organization);

		// In a group select Team selling
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// To Enter the value in dropdown
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));

		// Call the handledDropdown()
		wutil.handledDropdown(dropdown, Group);

		// To click on save button

		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();

		Thread.sleep(4000);

		// Mousehover on img
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		wutil.mouseHover(driver, image);

		// To click on signout button
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}
