package hello.lineup;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.lineup.Lineup;
import hello.lineup.LineupRepository;

@RestController
@RequestMapping("/lineup")
public class LineupController {
	@Autowired
	private LineupRepository lineupRepository;
	

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewLineup (@RequestParam Integer event
			, @RequestParam(required=false) String artist
			, @RequestParam(required=false) boolean is_headliner
			, @RequestParam(required=false) String mbid
			, @RequestParam(required=false) String setlist_id
			
			) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Lineup n = new Lineup();
		n.setEvent(event);
		n.setArtist(artist);
		n.setIs_headliner(is_headliner);
		n.setMbid(mbid);
		n.setSetlist_id(setlist_id);
		lineupRepository.save(n);
		return "Lineup Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Lineup> getAllLineups() {
		// This returns a JSON or XML with the users
		return lineupRepository.findAll();
	}
	
}