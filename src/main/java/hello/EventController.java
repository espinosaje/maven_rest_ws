package hello;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.Event;
import hello.EventRepository;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
    /* private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

   @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewEvent (@RequestParam String name
			, @RequestParam Integer venue
			, @RequestParam String tour
			, @RequestParam Integer year
			, @RequestParam boolean fest
			) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Event n = new Event();
		n.setName(name);
		n.setVenue(venue);
		n.setTour(tour);
		n.setYear(year);
		n.setFest(fest);
		eventRepository.save(n);
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