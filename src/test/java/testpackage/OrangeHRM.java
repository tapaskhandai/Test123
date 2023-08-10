package testpackage;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class OrangeHRM {

	WebDriver driver;

	@Parameters("broswer")
	@BeforeTest
	public void initBroswer(@Optional("chrome") String broswer) {

		switch (broswer) {
		case "chrome":

			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

			break;

		case "edge":

			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

			break;
			
		case "safari":
 
			driver = new SafariDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

			break;

		default: 
			System.out.println("Broswer not found");
			break;
		}

	}

	
	@AfterTest
	public void tearDown() {

		driver.quit();
	}

	@Parameters("url")
	@Test
	public void launchApp(String url) {

		driver.get(url);

	}

	@Parameters({"username","password"})
	@Test
	public void enterCredentials(String username,String Password) {

		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button")).click();

	}

	@Test
	public void navigateToMyInfo() {

		driver.findElement(By.xpath("//*[text()='My Info']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void verifyMyInfo() {

		String actual = driver.findElement(By.xpath("//*[text()='Immigration']")).getText();
		String expected = "Immigration";
		AssertJUnit.assertEquals(actual, expected, "Actual Result is not matched with Expected Result");
	}

	@Test
	public void verifyLogOut() throws InterruptedException {

		driver.findElement(By.xpath("//i[contains(@class,'oxd-userdropdown-icon')]")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Thread.sleep(2000);
		boolean element = driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		AssertJUnit.assertEquals(element, true);

	}

}