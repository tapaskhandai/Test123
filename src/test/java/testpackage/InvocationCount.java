package testpackage;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class InvocationCount {
	
	@Test(invocationCount = 4, threadPoolSize = 2)
	public void randomeUserData() throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://randomuser.me/");
		Thread.sleep(3000);
		String name=driver.findElement(By.xpath("//p[text()='Hi, My name is']/following::p[@id='user_value']")).getText();
		driver.findElement(By.xpath("//li[@data-label='email']")).click();
		String email=driver.findElement(By.xpath("//p[text()='My email address is']/following::p[@id='user_value']")).getText();
		System.out.println(name);
		System.out.println(email);
		driver.close();
	}

}
