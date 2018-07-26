// Class is still in progress.

package hello.setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import hello.setlist.Venue;


@RestController
@RequestMapping("/setlist")
public class VenueController {
	//@Autowired
	//private Venue venue;
	
	//todo: Need to add headers for the app key and JSON format
	@GetMapping(path="/venue") // Map ONLY GET Requests
	public @ResponseBody String addNewEvent (@RequestParam String cityName
			, @RequestParam String stateCode			
			) {
		String request = "https://api.setlist.fm/rest/1.0/search/venues"+"?cityName="+cityName+"&stateCode="+stateCode;		
		RestTemplate restTemplate = new RestTemplate();
        Venue venue = restTemplate.getForObject(request, Venue.class);
        log.info(quote.toString());
	}
}