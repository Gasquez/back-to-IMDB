package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import Bean.Review;
import DAO.*;

public class Handlers {
	JFrame currentFrame = null;
	
	// done
	public boolean addReview(String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return daoAccess.addReview(date.getTime(), date.getTime(), title, release, producer, summary, kind, nationnality, actors);
	}
	
	// done
	public boolean updateReview(String oldTitle, String title, long release, String producer, String summary, String kind, String nationnality)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return daoAccess.updateReview(date.getTime(), oldTitle, title, release, producer, summary, kind, nationnality);
	}

	public Review openReview(JFrame frame, String title) 
	{
		currentFrame = frame;
		closeChildren();
		ReviewDAO daoAccess = new ReviewDAOImple();
		return daoAccess.getReview(title);
	}
	
	// done
	public Review getReview(String title)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		return daoAccess.getReview(title);
	}
	
	
	
	// done
	public List<String> getAllActors() 
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		return daoAccess.getAllActors();
	}
	
	// done
	public void removeReview(String title)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		if(daoAccess.removeReview(title)){	
			 	closeChildren();
			}
	} 
	
	// done
	public List<String> getAllReviews()
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		Map<String, Review> reviewsList = daoAccess.getAllReviews();
		List<String> reviewsTitle = new ArrayList();
		for(String title: reviewsList.keySet())
			reviewsTitle.add(title);
		return reviewsTitle;
	}

	/** 
	 * IHM
	**/
	public void addReview(String title)
	{
		JFrame newReview = new JFrame();
	}

	public boolean closeChildren()
	{
		if(currentFrame == null)
		{
			return false;
		} 
		else 
		{
			currentFrame.dispose();
			currentFrame = null;
			return true;
		}
	}	
}

