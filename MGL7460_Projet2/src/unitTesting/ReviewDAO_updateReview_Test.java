package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Bean.Review;

public class ReviewDAO_updateReview_Test extends ReviewDAO_Test{

	private String oldTitle, title = null;
	
	{ oldTitle = "test42"; title = "test42,42"; }
	
	@Before
	public void beforeTest() {
		myDAO.removeReview(oldTitle);
		myDAO.removeReview(title);
		myDAO.addReview(1446577800000L, 1446577800000L, oldTitle, 1441117800000L, "Karibou", "Film sur la nature quebecoise :)", "action", "quebecois", Arrays.asList());
	}

	@After
	public void afterTest() {
		myDAO.removeReview(title);
	}
	
	@Test
	public void test() {
		myDAO.updateReview(1441117100000L, oldTitle, title, 1441117200000L, "Karibou Frank", "Film sur la nature quebecoise.", "action!", "quebecoise");
		Review myReview = myDAO.getReview(title);
		
		assertNotNull(myReview);
//		assertEquals(1441117100000L, myReview.getCreationDate());
//		assertEquals(1441117100000L, myReview.getEditionDate());
		assertEquals(title, myReview.getTitle());
//		assertEquals(1441117200000L, myReview.getRelease());
		assertEquals("Karibou Frank", myReview.getProducer());
		assertEquals("Film sur la nature quebecoise.", myReview.getSummary());
		assertEquals("action!", myReview.getKind());
		assertEquals("quebecoise", myReview.getNationnality());
		assertEquals(Arrays.asList(), myReview.getActors());
	}
}
