package unitTesting;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReviewController_updateReview_Test extends ReviewController_Test {
	
private String oldTitle, title = null;
	
	{ oldTitle = "test42"; title = "test42,42"; }
	
	@Before
	public void beforeTest() {
		myHandler.removeReview(oldTitle);
		myHandler.removeReview(title);
		myHandler.addReview(oldTitle, 1441117800000L, "Karibou", "Film sur la nature quebecoise :)", "action", "quebecois", Arrays.asList());
	}
	
	@Test
	public void test() {
		boolean testResult = myHandler.updateReview(oldTitle, title, 1441117200000L, "Karibou Frank", "Film sur la nature quebecoise.", "action!", "quebecoise");
		assertEquals(true, testResult);
	}
	
	@After
	public void afterTest() {
		myHandler.removeReview(title);
	}
}
