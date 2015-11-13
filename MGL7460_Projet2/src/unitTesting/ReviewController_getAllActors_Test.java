package unitTesting;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ReviewController_getAllActors_Test extends ReviewController_Test {
	
	@Test
	public void test() {
		List<String> actors = myHandler.getAllActors();
		
		List<String> expectedActors = Arrays.asList("Guillemette Francois", "Tremblay Guy");
		
		assertEquals(expectedActors, actors);
	
	}
}
