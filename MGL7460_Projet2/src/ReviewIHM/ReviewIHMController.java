package ReviewIHM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

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
	
	public boolean modifyReview( String releaseString,
									String producer,
									String summary,
									String kind,
									String nationnality
								) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Boolean isModify = false;
		Date releaseDate = formatter.parse(releaseString);
		Long releaseLong = releaseDate.getTime();
		
		isModify = myController.updateReview(myReview.getTitle(), myReview.getTitle(), releaseLong, producer, summary, kind, nationnality);
		if(isModify){
			myReview = myController.getReview(myReview.getTitle());
		}
		return isModify;
		
	}
	
	public boolean removeReview(String reviewName) {
		return myController.removeReview(reviewName);
	}
}
