
import java.util.ArrayList;

import javax.swing.Spring;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Tatoc_Selenium {
	
	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.cssSelector("a")).click();
		driver.findElement(By.className("greenbox")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));
		String actualAnswer = driver.findElement(By.id("answer")).getAttribute("class");		
		Boolean condition = true;
		while(condition) {
			driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();
			WebElement childDiv = driver.findElement(By.id("child"));
			driver.switchTo().frame(childDiv);
			String expectedAnswer = driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			if(actualAnswer.equals(expectedAnswer)) {
				condition = false;
			}
		}
		driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();
		driver.switchTo().defaultContent();
		Actions act=new Actions(driver);
		// find element which needs to be dragged
		WebElement drag=driver.findElement(By.xpath(".//*[@id='dragbox']"));
		// find element where we need to drop
		WebElement drop=driver.findElement(By.xpath(".//*[@id='dropbox']"));
		act.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.findElement(By.cssSelector("#name")).sendKeys("hello world");
		driver.findElement(By.cssSelector("#submit")).click();
	    driver.switchTo().window(tabs2.get(0));
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
	    String token = driver.findElement(By.cssSelector("#token")).getText();
	    String[] splited = token.split("\\s+");
	    
	    Cookie token1 = new Cookie("Token", splited[1]);
	    driver.manage().addCookie(token1);
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(10)")).click();
	    Thread.sleep(2000);
		driver.close();
	}
}


