package unitTesting;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class ReviewController_addReview_Test extends ReviewController_Test {
	
	private String title;

	{ title = "Test" + Math.random() * 100; }
	
	@Test
	public void test() {
		long release = 1446575292000L;
		String producer = "Jean Patate";
		String summary = "tellement mauvais qu'il ne vaut mieux pas l'expliquer...";
		String kind = "Mauvais film";
		String nationnality = "Belge";
		String actorLastName1 = "Guillemette";
		String actorFirstName1 = "Francois";
		String actorLastName2 = "Tremblay";
		String actorFirstName2 = "Guy";
		
		List<String> actors = new ArrayList<String>();
		actors.add(actorLastName1 + " " + actorFirstName1);
		actors.add(actorLastName2 + " " + actorFirstName2);
		
		boolean testResult = myHandler.addReview(title, release, producer, summary, kind, nationnality, actors);
		assertEquals(true, testResult);
	}
	
	@After
	public void after() {
		myHandler.removeReview(title);
	}
}
