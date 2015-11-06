package DAO;

import java.util.List;
import java.util.Map;

import Bean.*;

public interface ReviewDAO {
	
	public Review getReview(String title);
	
	public boolean addReview(long creationDate, long editionDate, String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors);
		
	public boolean updateReview(long editionDate, String title, long release, String producer, String summary, String kind, String nationnality);
	
	public boolean removeReview(String title);

	public Map<String, Review> getAllReviews();
	
	public List<String> getAllActors();
}