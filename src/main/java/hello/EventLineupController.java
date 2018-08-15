package hello;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

import hello.Event;
import hello.EventRepository;

import hello.lineup.Lineup;
import hello.lineup.LineupRepository;

@RestController
@RequestMapping("/event_lineup")
public class EventLineupController {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private LineupRepository lineupRepository;
	
	
	// @ResponseBody means the returned String is the response, not a view name
	// @RequestParam means it is a parameter from the GET or POST request
	@GetMapping(path="/add_event_headliner") // Map ONLY GET Requests
	public @ResponseBody String addNewEvent (@RequestParam(required=true) String name
			, @RequestParam(required=false) Integer venue
			, @RequestParam(required=false) String tour
			, @RequestParam(required=false) Integer year
			, @RequestParam(required=false) boolean fest
			, @RequestParam(required=false) 
			@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date
			, @RequestParam(required=false) String artist
			, @RequestParam(required=false) boolean is_headliner
			, @RequestParam(required=false) String mbid
			, @RequestParam(required=false) String setlist_id
			) {

		// Create the event first so we can get the ID to populate the lineup
		Event n = new Event();
		n.setName(name);
		n.setVenue(venue);
		n.setTour(tour);
		n.setYear(year);
		n.setFest(fest);
		n.setDate(date);
		eventRepository.saveAndFlush(n);	//changed "save" to "saveAndFlush" so we can get the ID
		System.out.print("Event ID: "+n.getId());
		// Create the Lineup for the event
		Lineup lineup = new Lineup();
		lineup.setEvent(n.getId());
		lineup.setArtist(artist);
		lineup.setIs_headliner(is_headliner);
		lineup.setMbid(mbid);
		lineup.setSetlist_id(setlist_id);
		lineupRepository.save(lineup);
		return "Event Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Event> getAllEvents() {
		// This returns a JSON or XML with the users
		return eventRepository.findAll();
	}
	
	//added later to query by event name
	@GetMapping(path="/get") // Map ONLY GET Requests
	public @ResponseBody Iterable<Event> findByName (@RequestParam String name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		
		return eventRepository.findByName(name);
	}
	
	//not working, might need to implement Entities for the other tables
	@GetMapping(path="/concert") // Map ONLY GET Requests
	public @ResponseBody Iterable<Event> findBySearchTerm (@RequestParam Integer searchTerm) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		
		return eventRepository.findBySearchTerm(searchTerm);
	}

}