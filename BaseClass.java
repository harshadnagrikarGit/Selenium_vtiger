package commonutils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	WebDriver driver = new ChromeDriver();
	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	
	@BeforeSuite
	public void BS()  {
		
		System.out.println("Connect to database");

	}

	@BeforeClass
	public void BC() throws IOException {
		
		//WebDriver driver = new ChromeDriver(); ;
		String url = putil.getDataFromPropertyFile("Url");
		
		// @Before class - used to launch the application

		// To maximize the window
		wutil.maximize(driver);

		// To apply wait for findlement()
		wutil.implicitlyWait(driver); 
		
		// To launch the application
		driver.get(url); 

	}

	@AfterClass
	public void AC()  {
		// @AfterClass  - is used to the close the browser
	
		driver.quit();

	}

	@BeforeMethod
	public void BM() throws IOException {
		// @BeforeMethod - is used to login to the application
	
		// To Reading the data from property file

		String username = putil.getDataFromPropertyFile("Username");
		String password = putil.getDataFromPropertyFile("Password");

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

	}

	@AfterMethod
	public void AM() throws InterruptedException  {
		// @AfterMethod  - is used to sign out from the application
		
		Thread.sleep(4);
		// Mousehover on img
		WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		wutil.mouseHover(driver, image);

		// To click on signout button
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

	@AfterSuite
	public void AS() {
		
		System.out.println("Disconnect from database");
	}

}
