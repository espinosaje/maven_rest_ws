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

import hello.setlist.Venue;


@RestController
@RequestMapping("/setlist")
public class VenueController {
	//@Autowired
	//private Venue venue;
	
	@GetMapping(path="/venue")
	public @ResponseBody String getVenue (@RequestParam String cityName
			, @RequestParam String stateCode			
			) {
		String request = "https://api.setlist.fm/rest/1.0/search/venues"+"?cityName="+cityName+"&stateCode="+stateCode;		
		RestTemplate restTemplate = new RestTemplate();

		System.out.println("request: "+request);
		
		
		// set the headers for authentication
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-api-key", "2f7b7e46-b66a-4281-8ac4-821cf1a03efb");
		HttpEntity<String> entityReq = new HttpEntity<String>(headers);
		
	
		//actual service call
        //Venue venue = restTemplate.getForObject(request, Venue.class);
		ResponseEntity<Venue> venue = (ResponseEntity<Venue>) restTemplate.exchange(request, HttpMethod.GET, entityReq, Venue.class);

		System.out.println("venue.getStatusCode: "+venue.getStatusCode());
		String result = (venue.getBody()).toString();
		
		return result;
	}
}