package ReviewIHM;

import Bean.Review;
import controllers.Handlers;

public class ReviewIHMController {
	private Handlers myController = null;
	private Review myReview = null;
	
	public ReviewIHMController( Handlers myController, Review myReview ) {
		this.myController = myController;
		this.myReview = myReview;
	}
	
	public Review getReview() {
		return myReview;
	}
	
	public boolean modifyReview() {
		
		
		
		return false;
	}
	
	public boolean deleteReview(String reviewName) {
		
		
		
		return false;
		
	}
}
