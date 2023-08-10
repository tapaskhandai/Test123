package listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

//public class IlistenerTestClass implements IListeners{ we can directly implement Ilisteners interface or extend the below class)

public class IlistenerTestClass extends TestListenerAdapter {


	@Override
	public void onTestSuccess(ITestResult tr) {

		System.out.println("onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("onTestFailure");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		System.out.println("onTestSkipped");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult tr) {
		System.out.println("onTestFailedWithTimeout");
	}

	@Override
	public void onStart(ITestContext testContext) {

		System.out.println("onStart");
		
	}

	@Override
	public void onFinish(ITestContext testContext) {
		
		System.out.println("onFinish");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");
	}

}
