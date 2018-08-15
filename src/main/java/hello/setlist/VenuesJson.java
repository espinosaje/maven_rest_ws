package hello.setlist;

import hello.setlist.SetlistVenue;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VenuesJson {

    private ArrayList<SetlistVenue> venue;
	
	private String type;
	

    public VenuesJson() {
    }
	
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SetlistVenue> getVenue() {
        return venue;
    }

    public void setVenue(ArrayList<SetlistVenue> venue) {
        this.venue = venue;
    }
/*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
*/
    @Override
    public String toString() {
		String result = "[";	//ToDo: change to StringBuffer or Builder
		System.out.println("venue.size(): "+venue.size());
		for (int i=0; i < venue.size(); i++){
			result += ((ArrayList<SetlistVenue>) this.getVenue()).get(i).toString()+",";
		}
		result = result.substring(0, result.lastIndexOf(",")) + "]";
		//result = "]";
		
        return result;
    }
	
	public Iterable<SetlistVenue> response() {
		Iterable<SetlistVenue> result = venue;
		
        return result;
    }
}