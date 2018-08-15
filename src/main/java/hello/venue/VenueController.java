package hello.venue;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.venue.Venue;
import hello.venue.VenueRepository;

@RestController
@RequestMapping("/venue")
public class VenueController {
	@Autowired
	private VenueRepository venueRepository;
	

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewVenue (@RequestParam String name
			, @RequestParam(required=false) String country
			, @RequestParam(required=false) String city
			, @RequestParam(required=false) String state
			, @RequestParam(required=false) String setlist_fm
			
			) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Venue n = new Venue();
		n.setName(name);
		n.setCountry(country);
		n.setCity(city);
		n.setState(state);
		n.setSetlist_fm(setlist_fm);
		venueRepository.save(n);
		return "Venue Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Venue> getAllVenues() {
		// This returns a JSON or XML with the users
		return venueRepository.findAll();
	}
	
}