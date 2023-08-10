package testpackage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FirstTest {

	WebDriver driver;
	SoftAssert soft = new SoftAssert();

	@BeforeMethod
	public void setUp() {

		System.out.println("Test Started");
//		ChromeOptions ops = new ChromeOptions();
//		ops.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@Test
	public void testGoogle() {

		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//a[text()='Gmail']")).click();

	}

	@Test
	public void testFacebook() {

		driver.get("https://www.facebook.com/");
		driver.findElement(By.name("email")).sendKeys("Tapas");
		driver.findElement(By.name("login")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//title verify
		AssertJUnit.assertEquals(driver.getTitle(), "Log in to Facebook", "Title is mismatched");

		//url verify

		AssertJUnit.assertTrue(driver.getCurrentUrl().contains("www.facebook.com"));
		
		//username text verify
		
		AssertJUnit.assertEquals(driver.findElement(By.name("email")).getAttribute("value"), "Tapas","Username text mismatched");
		
		//border verify
		
		String expectedBorder="1px solid rgb(221, 223, 226)";
		String actualBorder=driver.findElement(By.name("email")).getCssValue("boder");
		AssertJUnit.assertEquals(actualBorder, expectedBorder,"Border is mismatched");
		//assertEquals(actualBorder, expectedBorder,"Border is mismatched");
		
		//error text verify
		
		String expectedText="The password that you've entered is incorrect. Forgotten password?";
		AssertJUnit.assertEquals(driver.findElement(By.xpath("//div[@id='email_container']/following::div[1]/div[2]")).getText(), expectedText,"Error text is mismatched");
		soft.assertAll();
	}


	@AfterMethod
	public void tearDown() {

		driver.close();
		System.out.println("Test Completed");
	}

}
