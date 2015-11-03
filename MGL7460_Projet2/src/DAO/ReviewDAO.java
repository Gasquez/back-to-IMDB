package DAO;

import java.util.List;

import Bean.*;

public interface ReviewDAO {
	
	public Review getReview(String title);
	
	public void addReview(long creationDate, long editionDate, String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors);
		
	public void removeReview(String title);
}
