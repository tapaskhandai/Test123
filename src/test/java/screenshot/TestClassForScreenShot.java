package screenshot;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestClassForScreenShot extends Base{
	
	SoftAssert soft = new SoftAssert();
	
	@Test
	public void testGmail() {

		System.out.println(x);
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//a[text()='Gmail']")).click();
		String actual=driver.getTitle();
		String expected="Gmail";
		Assert.assertEquals(actual, expected,"Title is not matched");
		
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
	
	@Test
	public void testOrangeHrm() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		boolean present=driver.findElement(By.xpath("//*[text()='User Management']")).isDisplayed();
		Assert.assertTrue(present,"Element is not Present");

		
	}


}
