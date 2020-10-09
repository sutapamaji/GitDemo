
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase2 {

	WebDriver driver;

	@Test(dataProvider = "dataProvider")
	public void validateTestUrl(String url) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"F:\\SutapaMaji-V0.3-09-09-2020\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		try {
			// step1
			driver.get(url);
			System.out.println("Test url loaded successfully" + driver.getTitle());
			// Thread.sleep(10);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			scrollTillEnd();
			WebElement faceBook = driver.findElement(By.xpath("//*[@id='fbcount']/iframe"));
			driver.switchTo().frame(faceBook);
			WebElement faceBookButton = driver
					.findElement(By.xpath("/html/body/div/div/div/table/tbody/tr/td/div/div/button/span"));
			faceBookButton.click();
			WebElement secondWindow = driver.findElement(By.xpath("//div[contains(text(),'Log Into Facebook')]"));
			driver.switchTo().frame(secondWindow);
			String mainWindow = driver.getWindowHandle();
			driver.switchTo().window(mainWindow);
			int delhiTemp =Integer.parseInt (driver.findElement(By.xpath("//span[@class='current']")).getText());
			System.out.println(" Delhi current temperature is : " + delhiTemp);
			//step8
			driver.findElement(By.xpath("//div[@class='header']/a[@role='button']")).click();
			WebElement search=driver.findElement(By.xpath("//div[@class='add-loc-as-container']/input"));
			search.sendKeys("Bengaluru North, Ka");
			driver.findElement(By.xpath("//p[contains(text(),'RECENT SEARCHES')]")).click();
			driver.findElement(By.xpath("//button[@title='Search']")).click();
			//step9
			String text=driver.findElement(By.xpath("//div[@class='header']/span")).getText();
			if(text.contains("Bengaluru")) {
				System.out.println("Bengaluru selected successfully from the dropdown");
			}else {
				System.out.println("Bengaluru not selected from the dropdown");
			}
			//step10
			int bangaluruTemp = Integer.parseInt (driver.findElement(By.xpath("//span[@class='current']")).getText());
			System.out.println(" Begaluru current temperature is : " + bangaluruTemp);
			//step11
			if(delhiTemp > bangaluruTemp) {
				System.out.println("Delhi's temperature is higher than Bangaluru's temperature");
			}else {
				System.out.println("Bnagaluru's temperature is higher than Delhi's temperature");
			}
			//step12
			driver.close();
			
		} catch (NoSuchElementException e) {
			System.out.println("not able to verify the url");
		}
	}

	// scroll functionality

	public void scrollTillEnd() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int height = Integer.parseInt(jse.executeScript("return document.body.scrollHeight").toString());
		while (height > 100) {
			jse.executeScript("window.scrollBy(0, 100)");
			height -= 100;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@DataProvider
	public String[][] dataProvider() {
		return new String[][] {
				{ "http://www.msn.com/en-in/weather/today/New-Delhi,Delhi,India/we-city-28.608,77.201?iso=IN" },

		};
	}
}
