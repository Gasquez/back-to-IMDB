package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Bean.Review;

public class ReviewDAO_getAllReviews_Test extends ReviewDAO_Test {

	@Test
	public void test() {
		Map<String, Review> myReview = myDAO.getAllReviews();
		
		List<String> expectedActors = Arrays.asList("Tremblay Guy");
		
		assertNotNull(myReview);
		assertEquals(new Timestamp(1446526800000L), myReview.get("test1").getCreationDate());
		assertEquals(new Timestamp(1446526800000L), myReview.get("test1").getEditionDate());
		assertEquals("test1", myReview.get("test1").getTitle());
		assertEquals(new Timestamp(1446526800000L), myReview.get("test1").getRelease());
		assertEquals("Arthuer", myReview.get("test1").getProducer());
		assertEquals("Aucun", myReview.get("test1").getSummary());
		assertEquals("Action", myReview.get("test1").getKind());
		assertEquals("Francais", myReview.get("test1").getNationnality());
		assertEquals(expectedActors, myReview.get("test1").getActors());
	}
}
