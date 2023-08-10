package testpackage;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class DependsOnMethod {

	public String trackNumber = null;

	
	@Test(enabled = false)
	public void createPackage() {

		System.out.println(5 / 0);

		System.out.println("Create Package");
		trackNumber = "TA123PAS";
	}

	@Test(dependsOnMethods = { "createPackage" })
	public void trackPackage() throws Exception {

		if (trackNumber != null) {

			System.out.println("Track Package");
		} else {
			throw new Exception("Invalid Tracking Number");
		}
	}

	@Test(dependsOnMethods = { "createPackage" })
	public void cancelPackage() throws Exception {

		if (trackNumber != null) {

			System.out.println("Cancel Package");
		} else {
			throw new Exception("Can't Cancel the Package");
		}
	}

}
