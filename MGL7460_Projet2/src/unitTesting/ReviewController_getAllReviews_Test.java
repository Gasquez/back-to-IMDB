package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import Bean.Review;

public class ReviewController_getAllReviews_Test extends ReviewController_Test {
	@Test
	public void test() {
		List<String> myReview = myHandler.getAllReviews();
		
		List<String> expectedActors = Arrays.asList("Tremblay Guy");
		
		assertNotNull(myReview);
	}
}