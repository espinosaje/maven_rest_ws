package hello.setlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    private String name;
	private String state;
	private String stateCode;
	
    public City() {
    }
	
	
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "{" +
                "\"stateCode\":\"" + stateCode + "\"," +
                "\"name\":\"" + name + "\"" +
                '}';
    }
}