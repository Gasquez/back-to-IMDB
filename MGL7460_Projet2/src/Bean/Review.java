package Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Review {
	
	Timestamp creationDate;
	Timestamp editionDate;
	String title;
	Timestamp release;
	String producer;
	String summary;
	String kind;
	String nationnality;
	List<String> actors;
	
	public Review(Timestamp creationDate, Timestamp editionDate, String title, Timestamp release, String producer, String summary, String kind, String nationnality, List<String> actors) {
		this.creationDate = creationDate;
		this.editionDate = editionDate;
		this.title = title;
		this.release = release;
		this.producer = producer;
		this.summary = summary;
		this.kind = kind;
		this.nationnality = nationnality;
		this.actors = new ArrayList<String>();
		for (String actor: actors) {
			this.actors.add(actor);
		}

	}
	
	public void addActor(String actor) {
		this.actors.add(actor);
	}
	
	public long getCreationDate() {
		return creationDate.getTime();
	}
	
	public long getEditionDate() {
		return editionDate.getTime();
	}
	
	public String getTitle() {
		return title;
	}
	
	public long getRelease() {
		return release.getTime();
	}
	
	public String getProducer() {
		return producer;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getKind() {
		return kind;
	}
	
	public String getNationnality() {
		return nationnality;
	}
	
	public List<String> getActors() {
		return actors;
	}
}
