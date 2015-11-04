package DAO;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
			statement = (PreparedStatement) connexion.prepareStatement("SELECT * FROM review,actor WHERE review.title= ? AND review.title = actor.review_title;");
			statement.setString(1, title);

			rs = statement.executeQuery();
			
			long creationDate = 0;
			long editionDate = 0;
			long release = 0;
			String producer = "";
			String summary = "";
			String kind = "";
			String nationnality = "";
			List<String> actors = new ArrayList<String>();
								
			while (rs.next()) {
				if (rs.isFirst()) {
					hasResult = true;
					
					creationDate = rs.getTimestamp("creationDate").getTime();
					editionDate = rs.getTimestamp("editionDate").getTime();
					release = rs.getTimestamp("release").getTime();
					producer = rs.getString("producer");
					summary = rs.getString("summary");
					kind = rs.getString("kind");
					nationnality = rs.getString("nationnality");
				}
				actors.add(rs.getString("lastename") + " " + rs.getString("firstname"));
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

	public void addReview(long creationDate, long editionDate, String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors) {
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
					
					statement = (PreparedStatement) connexion.prepareStatement("INSERT INTO actor VALUES (?,?,?);");
					statement.setString(1, title);
					statement.setString(2, lastname);
					statement.setString(3, firstname);
					
					statement.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
	}

	public void removeReview(String title) {
		Connection connexion = (Connection) DBManager.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = (PreparedStatement) connexion.prepareStatement("DELETE FROM review WHERE title=?");
			statement.setString(1, title);
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.getInstance().cleanup(connexion, statement, rs);
		}
	}
}
