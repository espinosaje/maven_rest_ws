package hello.setlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.setlist.City;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SetlistVenue {

    private String id;
    private String name;
	private City city;
	
	
    public SetlistVenue() {
    }
	
	public SetlistVenue(String id,
						String name,
						City city) {
		this.id = id;
		this.name = name;
		this.city = city;
    }	
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"name\":\"" + name + "\"" +
                '}';
    }
}