package unitTesting;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Bean.Review;

public class ReviewController_removeReview_Test extends ReviewController_Test {
	private String title;

	{ title = "Test" + Math.random() * 100; }
	
	@Test
	public void test() {
		long release = 1446575292;
		String producer = "Jean Patate";
		String summary = "tellement mauvais qu'il ne faut mieux pas l'expliquer...";
		String kind = "Mauvais film";
		String nationnality = "Belge";
		String actorLastName1 = "Guillemette";
		String actorFirstName1 = "Francois";
		String actorLastName2 = "Tremblay";
		String actorFirstName2 = "Guy";
		
		List<String> actors = new ArrayList<String>();
		actors.add(actorLastName1 + " " + actorFirstName1);
		actors.add(actorLastName2 + " " + actorFirstName2);
		
		myHandler.addReview(title, release, producer, summary, kind, nationnality, actors);
		myHandler.removeReview(title);

		Review myReview = myHandler.getReview(title);
		
		assertEquals(null, myReview);
	}
}
