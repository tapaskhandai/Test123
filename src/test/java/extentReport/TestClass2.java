	package extentReport;
	
	import java.util.Set;
	
	import org.openqa.selenium.By;
	import org.testng.annotations.Test;
	
	public class TestClass2 extends BaseClass {
	
		@Test
		public void testSwitchWindow() throws InterruptedException {
	
			driver.get("https://demoqa.com/browser-windows");
			extentTest.info("Navigate to Tools QA");
			String parentWindow = driver.getWindowHandle();
			driver.findElement(By.xpath("//*[@id='tabButton']")).click();
			Thread.sleep(4000);
	     	extentTest.info("Clicked on New Window");
	
			Set<String> allWindows = driver.getWindowHandles();
			for (String child : allWindows) {
				if (!parentWindow.contentEquals(child)) {
					driver.switchTo().window(child);
					extentTest.info("Switched to child window:" + child);
					String childTitle = driver.getTitle();
					System.out.println(childTitle);
					System.out.println(driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
			extentTest.info("Successfully Switched to Parent window");
			driver.findElement(By.xpath("//*[@id='windowButton']")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			for (String child2 : driver.getWindowHandles()) {
	
				if (!parentWindow.contentEquals(child2)) {
					driver.switchTo().window(child2);
					driver.manage().window().maximize();
					extentTest.info("Switched to child window2:" + child2);
					String childTitle = driver.getTitle();
					System.out.println(childTitle);
	
				}
	
			}
		}
	
	}
