package hello.setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.ArrayList;

import hello.setlist.VenuesJson;
import hello.setlist.SetlistVenue;

import hello.venue.Venue;
import hello.venue.VenueRepository;

import hello.setlist.City;

@RestController
@RequestMapping("/setlist")
public class SetlistVenueController {
	@Autowired
	private VenueRepository venueRepository;
	
	HttpEntity<String> entityReq;
	
	public SetlistVenueController(){
		// set the headers for authentication
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-api-key", "2f7b7e46-b66a-4281-8ac4-821cf1a03efb");
		entityReq = new HttpEntity<String>(headers);		
	}
	
	public SetlistVenue lookForLocalVenue(String name
			, String stateCode
			, String cityName){
		//1. call repositofy findByName
		Venue venueInDB = venueRepository.findByName(name);
		
		//2. if found form a SetlistVenue object
		if (null != venueInDB){
			City city = new City(
				venueInDB.getCity(),
				null, //state name (not code)
				venueInDB.getState(),
				venueInDB.getCountry());
				
			SetlistVenue result = new SetlistVenue(
				venueInDB.getId()+"",
				venueInDB.getName(),
				city);	
				
			
			//ArrayList<SetlistVenue> result = null;
			
			return result;
		}
		
		//3. Otherwise, return NULL
		return null;		
	}

	@GetMapping(path="/venue")
	public @ResponseBody Iterable<SetlistVenue> getVenue (@RequestParam(required=false) String name
			, @RequestParam(required=false) String stateCode
			, @RequestParam(required=false) String cityName
			, @RequestParam(required=false) boolean lookForLocalDatabaseFirst	
			) {
		
		if (lookForLocalDatabaseFirst){
			SetlistVenue localVenue = lookForLocalVenue(name,stateCode,cityName);
			
			if (null != localVenue){
				ArrayList<SetlistVenue> result = new ArrayList<SetlistVenue>();
				result.add(localVenue);
				return 	result;
			}
		}	
				
		String request = "https://api.setlist.fm/rest/1.0/search/venues?";

		if (name != null && !name.equals("")){
			request += "name="+name+"&";
		}
		
		if (stateCode != null && !stateCode.equals("")){
			request += "stateCode="+stateCode+"&";
		}
		
		if (cityName != null && !cityName.equals("")){
			request += "cityName="+cityName+"&";
		}
				
		System.out.println("request: "+request);		
		
		RestTemplate restTemplate = new RestTemplate();

		//actual service call
		ResponseEntity<VenuesJson> venuesJsonEntity = (ResponseEntity<VenuesJson>) restTemplate.exchange(request, HttpMethod.GET, entityReq, VenuesJson.class);	
		System.out.println("venuesJsonEntity.getStatusCode: "+venuesJsonEntity.getStatusCode());

		//get the main JSON object from the entity
		VenuesJson venuesJSon = (VenuesJson) venuesJsonEntity.getBody();
		
		System.out.println("venuesJSon: "+venuesJSon);

		return venuesJSon.response();
	}
}