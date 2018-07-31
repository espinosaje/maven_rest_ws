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
import hello.setlist.Venue;


@RestController
@RequestMapping("/setlist")
public class VenueController {
	//@Autowired
	//private Venue venue;
	HttpEntity<String> entityReq;
	
	public VenueController(){
		// set the headers for authentication
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-api-key", "2f7b7e46-b66a-4281-8ac4-821cf1a03efb");
		entityReq = new HttpEntity<String>(headers);
		
	}
	
	@GetMapping(path="/venue")
	public @ResponseBody Iterable<Venue> getVenue (@RequestParam(required=false) String name
			, @RequestParam(required=false) String stateCode
			, @RequestParam(required=false) String cityName			
			) {
				
		String request = "";;

		if (name != null && !name.equals("")){
			request = "https://api.setlist.fm/rest/1.0/search/venues"+"?name="+name;
		}
		else if (stateCode != null && cityName != null){
			request = "https://api.setlist.fm/rest/1.0/search/venues"+"?cityName="+cityName+"&stateCode="+stateCode;
		}
		
		System.out.println("request: "+request);		
		
		RestTemplate restTemplate = new RestTemplate();

		//actual service call
		ResponseEntity<VenuesJson> venuesJsonEntity = (ResponseEntity<VenuesJson>) restTemplate.exchange(request, HttpMethod.GET, entityReq, VenuesJson.class);	
		System.out.println("venuesJsonEntity.getStatusCode: "+venuesJsonEntity.getStatusCode());

		//get the main JSON object from the entity
		VenuesJson venuesJSon = (VenuesJson) venuesJsonEntity.getBody();

		return venuesJSon.response();
	}
}