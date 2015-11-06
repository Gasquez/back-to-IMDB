package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Bean.Review;
import DAO.ReviewDAO;
import DAO.ReviewDAOImple;

public class ReviewDAO_getReview_Test extends ReviewDAO_Test {

	@Test
	public void test() {
		Review myReview = myDAO.getReview("test1");
		
		List<String> expectedActors = Arrays.asList("Tremblay Guy");
		
		assertNotNull(myReview);
		assertEquals(1446526800000L, myReview.getCreationDate());
		assertEquals(1446526800000L, myReview.getEditionDate());
		assertEquals("test1", myReview.getTitle());
		assertEquals(1446526800000L, myReview.getRelease());
		assertEquals("Arthuer", myReview.getProducer());
		assertEquals("Aucun", myReview.getSummary());
		assertEquals("Action", myReview.getKind());
		assertEquals("Francais", myReview.getNationnality());
		assertEquals(expectedActors, myReview.getActors());
	}
}
