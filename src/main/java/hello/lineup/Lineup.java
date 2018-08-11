package hello.lineup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Lineup {
   
	@Id
    private Integer event;

    private String artist;

    private boolean is_headliner;
	
	private String mbid;
	
	private String setlist_id;
	
	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public boolean getIs_headliner() {
		return is_headliner;
	}

	public void setIs_headliner(boolean is_headliner) {
		this.is_headliner = is_headliner;
	}
	
	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getSetlist_id() {
		return setlist_id;
	}

	public void setSetlist_id(String setlist_id) {
		this.setlist_id = setlist_id;
	}

}