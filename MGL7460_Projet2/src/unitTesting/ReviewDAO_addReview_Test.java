package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import Bean.Review;

public class ReviewDAO_addReview_Test extends ReviewDAO_Test {
	
	private String title;

	{ title = "Test" + Math.random() * 100; }
	
	@Test
	public void test() {
		long creationDate = 1446585292;
		long editionDate = 1445585292;
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
		
		myDAO.addReview(creationDate, editionDate, title, release, producer, summary, kind, nationnality, actors);
		
		Review myReview = myDAO.getReview(title);
		
		List<String> expectedActors = Arrays.asList(actorLastName1 + " " + actorFirstName1, actorLastName2 + " " + actorFirstName2);
		
		assertNotNull(myReview);
//		assertEquals(creationDate, myReview.getCreationDate());
//		assertEquals(editionDate, myReview.getEditionDate());
		assertEquals(title, myReview.getTitle());
//		assertEquals(release, myReview.getRelease());
		assertEquals(producer, myReview.getProducer());
		assertEquals(summary, myReview.getSummary());
		assertEquals(kind, myReview.getKind());
		assertEquals(nationnality, myReview.getNationnality());
		assertEquals(expectedActors, myReview.getActors());
	}

	@After
	public void after() {
		myDAO.removeReview(title);
	}
}
