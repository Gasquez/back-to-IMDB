package unitTesting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ReviewDAO_getAllActors_Test extends ReviewDAO_Test {

	@Test
	public void test() {
		List<String> actors = myDAO.getAllActors();
		
		List<String> expectedActors = Arrays.asList("Tremblay Guy", "Guillemette Francois");
		
		assertEquals(expectedActors, actors);
	}
}
