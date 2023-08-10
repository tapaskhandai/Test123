package listener;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners({ IlistenerTestClass.class })
public class RealScenarioTestClass {

	WebDriver driver;

	@BeforeTest
	public void initiate() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

		
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 0)
	public void test1() {

		driver.get("https://randomuser.me/");
		driver.findElement(By.xpath("//a[@href='photos']")).click();
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//section[@id='men']")).isDisplayed());
	}

	@Test(priority = 1)
	public void test2() throws InterruptedException {

		driver.get("https://www.makemytrip.com/");
		Thread.sleep(2000);
		AssertJUnit.assertTrue(driver.getTitle().contains("Make"));

	}

	@Test(priority = 2)
	public void test3() {

		System.out.println("test3 method");
		AssertJUnit.assertTrue(false);
	}

	@Test(timeOut = 1000)
	public void test4() throws Exception {

		Thread.sleep(2000);
		System.out.println("test4 method");

	}

	@Test(dependsOnMethods = { "test4" })
	public void test5() {
		System.out.println("test5 method");
	}

}
