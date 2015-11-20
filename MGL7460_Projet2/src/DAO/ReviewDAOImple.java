package DAO;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.Review;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ReviewDAOImple implements ReviewDAO {

	public Review getReview(String title) {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean hasResult = false;
		
		Review myReview = null;
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement(
					"SELECT * FROM review "
					+ "LEFT OUTER JOIN "
					+ "(SELECT * "
					+ "FROM actorreview,actor "
					+ "WHERE actorreview.actor_lastname=actor.lastname "
					+ "AND actorreview.actor_firstname=actor.firstname) AS T "
					+ "ON review.title = T.review_title "
					+ "WHERE title=? "
					+ ";");
			statement.setString(1, title);

			rs = statement.executeQuery();
			
			Timestamp creationDate = null;
			Timestamp editionDate = null;
			Timestamp release = null;
			String producer = "";
			String summary = "";
			String kind = "";
			String nationnality = "";
			List<String> actors = new ArrayList<String>();
								
			while (rs.next()) {
				String lastName = rs.getString("lastname");
				String firstName = rs.getString("firstname");
				
				if (rs.isFirst()) {
					hasResult = true;
					
					creationDate = rs.getTimestamp("creationDate");
					editionDate = rs.getTimestamp("editionDate");
					release = rs.getTimestamp("release");
					producer = rs.getString("producer");
					summary = rs.getString("summary");
					kind = rs.getString("kind");
					nationnality = rs.getString("nationnality");
				}
				if (lastName != null && firstName != null)
					actors.add(lastName + " " + firstName);
			}
			
			if (hasResult)
				myReview = new Review(creationDate,	editionDate, title, release, producer, summary, kind, nationnality, actors);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return myReview;
	}

	public boolean addReview(long creationDate, long editionDate, String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors) {
		if(actors == null)
			actors = new ArrayList<String>();
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			// Insert review
			statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO review VALUES (?,?,?,?,?,?,?,?);");
			statement.setTimestamp(1, new Timestamp(creationDate));
			statement.setTimestamp(2, new Timestamp(editionDate));
			statement.setString(3, title);
			statement.setTimestamp(4, new Timestamp(release));
			statement.setString(5, producer);
			statement.setString(6, summary);
			statement.setString(7, kind);
			statement.setString(8, nationnality);

			statement.executeUpdate();
						
			// Insert actors
			if (!actors.isEmpty()) {
				for (String actorName: actors) {
					String lastname = actorName.substring(0, actorName.indexOf(" "));
					String firstname = actorName.substring(actorName.indexOf(" ") + 1);
					
					statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO actorreview VALUES (?,?,?);");
					statement.setString(1, title);
					statement.setString(2, lastname);
					statement.setString(3, firstname);
					
					statement.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return true;
	}

	public boolean updateReview(long editionDate, String oldTitle, String title, long release, String producer, String summary, String kind, String nationnality) {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement("UPDATE review SET editionDate=?,title=?,`release`=?,producer=?,summary=?,kind=?,nationnality=? WHERE title=?");
			statement.setTimestamp(1, new Timestamp(editionDate));
			statement.setString(2, title);
			statement.setTimestamp(3, new Timestamp(release));
			statement.setString(4, producer);
			statement.setString(5, summary);
			statement.setString(6, kind);
			statement.setString(7, nationnality);
			statement.setString(8, oldTitle);			
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return true;
	}
	
	public boolean removeReview(String title) {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement("DELETE FROM review WHERE title=?");
			statement.setString(1, title);
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return true;
	}

	public Map<String, Review> getAllReviews() {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean hasResult = false;
		
		Map<String, Review> myMapReviews = new HashMap<String, Review>();
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM review,actor,actorreview WHERE review.title = actorreview.review_title AND actorreview.actor_lastname=actor.lastname AND actorreview.actor_firstname=actor.firstname;");
			rs = statement.executeQuery();

			String title = "";
			Timestamp creationDate = null;
			Timestamp editionDate = null;
			Timestamp release = null;
			String producer = "";
			String summary = "";
			String kind = "";
			String nationnality = "";
			String actorLastName = "";
			String actorFirstName = "";
								
			while (rs.next()) {
				title = rs.getString("title");
				creationDate = rs.getTimestamp("creationDate");
				editionDate = rs.getTimestamp("editionDate");
				release = rs.getTimestamp("release");
				producer = rs.getString("producer");
				summary = rs.getString("summary");
				kind = rs.getString("kind");
				nationnality = rs.getString("nationnality");
				actorLastName = rs.getString("lastname");
				actorFirstName = rs.getString("firstname");
				
				if (!myMapReviews.containsKey(title)) {
					// Create Review
					myMapReviews.put(
							title, 
							new Review(creationDate, editionDate, title, release, producer, summary, kind, nationnality, Arrays.asList(actorLastName + " " + actorFirstName)));
				} else {
					// Add actors
					myMapReviews.get(title).addActor(actorLastName + " " + actorFirstName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return myMapReviews;
	}

	public List<String> getAllActors() {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		List<String> myListActors = new ArrayList<String>();
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM actor;");
			rs = statement.executeQuery();

			String actorLastName = "";
			String actorFirstName = "";
								
			while (rs.next()) {
				actorLastName = rs.getString("lastname");
				actorFirstName = rs.getString("firstname");
				
				myListActors.add(
						actorLastName
						+ " "
						+ actorFirstName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
		
		return myListActors;
	}
}
