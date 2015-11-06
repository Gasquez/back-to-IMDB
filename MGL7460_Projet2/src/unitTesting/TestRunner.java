package unitTesting;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
   public static void main(String[] args) {
	  Result result = JUnitCore.runClasses(
			  ReviewDAO_getReview_Test.class,
			  ReviewDAO_getAllReviews_Test.class,
			  ReviewDAO_removeReview_Test.class,
			  ReviewDAO_addReview_Test.class,
			  ReviewDAO_updateReview_Test.class,
			  ReviewDAO_getAllActors_Test.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
} 