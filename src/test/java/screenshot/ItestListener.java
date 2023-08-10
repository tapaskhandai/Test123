package screenshot;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ItestListener extends TestListenerAdapter {

	Base instance1 = new Base();

	@Override
	public void onTestFailure(ITestResult tr) {

		//instance1.captureScreenshot(tr.getName() + System.currentTimeMillis() + ".jpg");
		instance1.captureScreenshot(tr.getTestContext().getName()+"_"+tr.getName() + System.currentTimeMillis() + ".jpg");

	}

	@Override
	public void onStart(ITestContext testContext) {

		System.out.println("Test Started");
	}

	@Override
	public void onFinish(ITestContext testContext) {

		System.out.println("Test Finished");
	}

}
