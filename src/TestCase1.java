

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase1 {

	@Test(dataProvider = "dataProvider")
	public void validateTestUrl(String url) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"F:\\SutapaMaji-V0.3-09-09-2020\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		try {

			driver.get(url);
			System.out.println("Test url loaded successfully" + driver.getTitle());
			driver.findElement(By.xpath("//a[@href='/en-in/weather' and @class='vertical']")).click();
			driver.findElement(By.xpath("//a[@href='/en-in/lifestyle/travel']")).click();
			driver.findElement(By.xpath("//a[@href='/en-in/lifestyle' and @class='vertical']")).click();
			driver.findElement(By.xpath("//a[@href='http://www.msn.com/en-in/travel']")).click();
			driver.navigate().back();
			driver.findElement(By.xpath("//a[@href='/en-in/lifestyle' and @class='vertical']")).click();
			driver.findElement(By.xpath("//a[@href='http://flights.msn.com/en-in/flight-search']")).click();
			driver.findElement(By.xpath("//a[contains(@class,'-cover populated')]")).sendKeys("Delhi");
			driver.findElement(By.xpath("//input[contains(@placeholder,'Destination city or airport')]"))
					.sendKeys("chennai");
			driver.findElement(By.xpath("//div[contains(@class,'day')]")).click();
			driver.findElement(By.xpath("//*[@id='date-depart_table']//div[contains(text(),'18')]")).click();
			driver.findElement(By.xpath("//a[@href='/en-in/lifestyle' and @class='vertical']")).click();
			driver.close();
		} catch (NoSuchElementException e) {
			System.out.println("not able to verify the url");
		}
	}

	@DataProvider
	public String[][] dataProvider() {
		return new String[][] {
				{ "http://www.msn.com/en-in/weather/today/New-Delhi,Delhi,India/we-city-28.608,77.201?iso=IN" },

		};
	}
}


