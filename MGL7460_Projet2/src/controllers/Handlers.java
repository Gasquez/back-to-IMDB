package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import Bean.Review;
import DAO.*;
import IHM.IHM;

public class Handlers {
	
	public Handlers() {
		new IHM(this);
	}
	
	// done
	public boolean addReview(String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return daoAccess.addReview(date.getTime(), date.getTime(), title, release, producer, summary, kind, nationnality, actors);
	}
	
	public boolean addReview(String title, long release)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getTime());
		return daoAccess.addReview(date.getTime(), date.getTime(), title, release, null, null, null, null, null);
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
	public boolean removeReview(String title)
	{
		ReviewDAO daoAccess = new ReviewDAOImple();
		return daoAccess.removeReview(title);
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
}

