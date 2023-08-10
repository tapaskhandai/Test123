package testpackage;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class NewTestG2 {

	@Test(groups = { "smoke", "build" })
	public void test6() {

		System.out.println("smoke and build");
	}

	@Test(groups = { "regression" })
	public void test7() {

		System.out.println("regression");
	}

	@Test(groups = { "smoke", "regression" })
	public void test8() {

		System.out.println("smoke and regression");
	}

	@Test(groups = { "sanity", "regression" })
	public void test9() {

		System.out.println("sanity and regression");
	}

	@Test
	public void test10() {

		System.out.println("No Group--NewtestG2");
	}

}
