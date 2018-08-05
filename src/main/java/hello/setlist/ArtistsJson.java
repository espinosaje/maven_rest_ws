package hello.setlist;

import hello.setlist.Artist;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistsJson {

    private ArrayList<Artist> artist;
	
	private String type;
	

    public ArtistsJson() {
    }
	
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Artist> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<Artist> artist) {
        this.artist = artist;
    }

/*
    @Override
    public String toString() {
		String result = "[";	//ToDo: change to StringBuffer or Builder
		System.out.println("venue.size(): "+venue.size());
		for (int i=0; i < venue.size(); i++){
			result += ((ArrayList<Venue>) this.getVenue()).get(i).toString()+",";
		}
		result = result.substring(0, result.lastIndexOf(",")) + "]";
		//result = "]";
		
        return result;
    }
	*/
	
	public Iterable<Artist> response() {
		Iterable<Artist> result = artist;
		
        return result;
    }
}