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
		
		List<String> expectedActors = Arrays.asList("Trembley Guy");
		
		assertNotNull(myReview);
		assertEquals(0, myReview.getCreationDate());
		assertEquals(0, myReview.getEditionDate());
		assertEquals("test1", myReview.getTitle());
		assertEquals(0, myReview.getRelease());
		assertEquals("Arthuer", myReview.getProducer());
		assertEquals("Aucun", myReview.getSummary());
		assertEquals("Action", myReview.getKind());
		assertEquals("Francais", myReview.getNationnality());
		assertEquals(expectedActors, myReview.getActors());
	}
}
