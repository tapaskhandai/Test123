package testpackage;

import org.testng.annotations.Test;
import org.testng.annotations.Test;


@Test(groups = {"All"})
public class NewTestGroups {

	@Test(groups = { "sanity" })
	public void test1() {

		System.out.println("sanity");
	}

	@Test(groups = { "regression" })
	public void test2() {

		System.out.println("regression");
	}

	@Test(groups = { "smoke" })
	public void test3() {

		System.out.println("smoke");
	}

	@Test(groups = { "build" })
	public void test4() {

		System.out.println("build");
	}

	@Test
	public void test5() {

		System.out.println("No group--NewTestGroups");
	}

}
