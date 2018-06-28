package test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGTatoc {
	
	public static WebDriver driver;
	
	public static void startChromeInstance() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuradhasingh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	public static void closeChrome() {
		driver.close();
	}
	
	
	



	

	
	
	
	//launch url
	public static void launchURL() {
		driver.get("http://10.0.1.86/tatoc");
	}
	
	
	//select Basic Course
	public void selectBasicCourse() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a[text()='Basic Course']")).click();
	}
	
	
	//Green Box click
	public static void selectGreenBox() {
		
		driver.findElement(By.className("greenbox")).click();
	}
	
	
	
	//Color Matching Box
	public static void boxColorMatching() {
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
	}
	
	
	
	
	//Drag and Drop
	public static void dragDrop() {
		Actions act=new Actions(driver);
		WebElement drag=driver.findElement(By.xpath(".//*[@id='dragbox']"));
		WebElement drop=driver.findElement(By.xpath(".//*[@id='dropbox']"));
		act.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();
	}
	
	
	
	// launch pop ups
	public static void launchPopUps() {
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.findElement(By.cssSelector("#name")).sendKeys("hello world");
		driver.findElement(By.cssSelector("#submit")).click();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	
	
	//Cookie Handling
	public static void cookieHandle() {
		driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
	    String token = driver.findElement(By.cssSelector("#token")).getText();
	    String[] splited = token.split("\\s+");
	    
	    Cookie token1 = new Cookie("Token", splited[1]);
	    driver.manage().addCookie(token1);
	    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(10)")).click();
	    
	}
	
	
	
	
	
	
	
	
	
	
	
	//Tests

	@Test
	public void Step_01_LaunchURL() {		  
		launchURL();
	}

	@Test
	public void Step_02_OpenLink() {
		selectBasicCourse();

	}
	@Test
	public void Step_03_SelectionOfGreenBox() {
		selectGreenBox();

	}
	@Test
	public static void Step_04_BoxColorMatch() {
		boxColorMatching();		
	}
	@Test
	public static void Step_05_DragAndDrop() {
		dragDrop();	
	}
	@Test
	public static void Step_06_PopUpWindows() {
		launchPopUps();	
	}
	@Test
	public static void Step_07_CookieHandling() {
		cookieHandle();	
	}
	


	
	
	
	
	
	
	
	
	
	@BeforeTest
	public void beforeTest() {
		startChromeInstance();
	}

	@AfterTest
	public void afterTest() {
		
		closeChrome();
	}
}
