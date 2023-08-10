package extentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extentreports;
	public static ExtentTest extentTest;

	@Parameters({ "broswer" })
	@BeforeTest
	public void setUp(ITestContext context, String broswer) {

		if (broswer.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		}

		else if (broswer.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		}

		else if (broswer.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		}

		else {
			System.out.println("Broswer is not available");
		}
		
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String deviceInfo = capabilities.getBrowserName() + ": " + capabilities.getBrowserVersion();
		String authorNmae = context.getCurrentXmlTest().getParameter("Author");
		
		extentTest = extentreports.createTest(context.getName());
		extentTest.assignAuthor(authorNmae);
		extentTest.assignDevice(deviceInfo);

	}
	
	@AfterMethod
	public void checkStatus(ITestResult tr) {

		if (tr.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = null;
			screenShotPath = captureScreenshot(
					tr.getTestContext().getName() + "_" + tr.getName() + System.currentTimeMillis() + ".jpg");
			extentTest.addScreenCaptureFromPath(screenShotPath);
			extentTest.fail(tr.getThrowable());

		} else if

		(tr.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(tr.getName() + " is passed");
		}

	}
	
	@BeforeMethod
	public void getGroupName(Method m) {
		
		String[] group=m.getAnnotation(Test.class).groups();
		extentTest.assignCategory(group);
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

	@BeforeSuite
	public void initializeExtentreport() {

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");

		sparkReporter.config().setReportName("Test Automation Report");
		sparkReporter.config().setDocumentTitle("Extent Report");
		sparkReporter.config().setTimeStampFormat("dd-MM-yy");

		extentreports = new ExtentReports();
		extentreports.attachReporter(sparkReporter);
		extentreports.setSystemInfo("Os", System.getProperty("os.name"));

	}

	@AfterSuite
	public void generateExtentReport() {
		
		extentreports.flush();
		try {
			Desktop.getDesktop().browse(new File("extentReport.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String captureScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShots2/" + fileName);

		try {
			FileUtils.copyFile(srcfile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("ScreenShot Saved Successfully");
		String absolute_path = destFile.getAbsolutePath();
		return absolute_path;

	}

}
