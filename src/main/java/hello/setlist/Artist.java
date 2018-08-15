package hello.setlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

    private String mbid;
    private String name;
    private String disambiguation;
	
    public Artist() {
    }
	
    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    @Override
    public String toString() {
        return "{" +
                "\"mbid\":\"" + mbid + "\"," +
                "\"name\":\"" + name + "\"" +
                '}';
    }
}