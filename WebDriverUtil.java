package commonutils;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtil {
	
	public void maximize(WebDriver driver) {
		
		driver.manage().window().maximize();
	}

	public void implicitlyWait(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void handledDropdown(WebElement element,String targetedElement) {
		
		Select s = new Select(element);
		s.selectByVisibleText(targetedElement);
	}
	
	public void mouseHover(WebDriver driver,WebElement element) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public void switchWindow(WebDriver driver,String expectedUrl) {
		
		Set<String> ids = driver.getWindowHandles();
		
		for(String a : ids ) {
			
			String actualUrl	= driver.switchTo().window(a).getCurrentUrl();
			
//			String expectedUrl =  "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
			
			if(actualUrl.contains(expectedUrl)) {
				break;
			}
		
	     }
	}
		
		public File screenshot(WebDriver driver,String fileName) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot) driver ;
			File tempFile = ts.getScreenshotAs(OutputType.FILE);
			
			File destinationFile = new File("./screenshot1/"+"screenshotName"+".png");
			
			FileUtils.copyFile(tempFile,destinationFile);
			return destinationFile;
			
			
		}
	}

