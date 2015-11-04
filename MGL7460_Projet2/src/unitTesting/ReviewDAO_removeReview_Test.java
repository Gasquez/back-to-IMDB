package unitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Bean.Review;
import DAO.ReviewDAO;
import DAO.ReviewDAOImple;

public class ReviewDAO_removeReview_Test extends ReviewDAO_Test {

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
		String actorLastName1 = "Bricoleur";
		String actorFirstName1 = "Bob";
		String actorLastName2 = "Clown";
		String actorFirstName2 = "Bozo";
		
		List<String> actors = new ArrayList<String>();
		actors.add(actorLastName1 + " " + actorFirstName1);
		actors.add(actorLastName2 + " " + actorFirstName2);
		
		myDAO.addReview(creationDate, editionDate, title, release, producer, summary, kind, nationnality, actors);
		myDAO.removeReview(title);

		Review myReview = myDAO.getReview(title);
		
		assertEquals(null, myReview);
	}
}
