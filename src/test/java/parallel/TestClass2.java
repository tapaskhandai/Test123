package parallel;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TestClass2 {

	@Test
	public void method5() {

		System.out.println("Class2>>>method5" + " "+Thread.currentThread().getId());
	}

	@Test
	public void method6() {

		System.out.println("Class2>>>method6" + " "+Thread.currentThread().getId());
	}

	@Test
	public void method7() {

		System.out.println("Class2>>>method7" + " "+Thread.currentThread().getId());
	}

}
