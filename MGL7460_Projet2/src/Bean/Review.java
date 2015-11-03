package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Relation;

public class Review {
	
	long creationDate;
	long editionDate;
	String title;
	long release;
	String producer;
	String summary;
	String kind;
	String nationnality;
	List<String> actors;
	
	{ actors = new ArrayList<String>(); }
	
	public Review(long creationDate, long editionDate, String title, long release, String producer, String summary, String kind, String nationnality, List<String> actors) {
		this.creationDate = creationDate;
		this.editionDate = editionDate;
		this.title = title;
		this.release = release;
		this.producer = producer;
		this.summary = summary;
		this.kind = kind;
		this.nationnality = nationnality;
		this.actors = actors;

	}
	
	public long getCreationDate() {
		return creationDate;
	}
	
	public long getEditionDate() {
		return editionDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public long getRelease() {
		return release;
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
