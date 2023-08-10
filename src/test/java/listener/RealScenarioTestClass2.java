package listener;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;

@Listeners({ IlistenerTestClass.class })
public class RealScenarioTestClass2 {
	
	

	@Test()
	public void test6() {

		System.out.println("test6 method");
		AssertJUnit.assertTrue(false);
	}

	@Test(timeOut = 1000)
	public void test7() throws Exception {

		Thread.sleep(2000);
		System.out.println("test7 method");

	}

	@Test(dependsOnMethods = { "test7" })
	public void test8() {
		System.out.println("test8 method");
	}

}
