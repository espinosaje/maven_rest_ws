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

import hello.setlist.ArtistsJson;
import hello.setlist.Artist;


@RestController
@RequestMapping("/setlist")
public class ArtistController {
	//@Autowired
	//private Venue venue;
	HttpEntity<String> entityReq;
	
	public ArtistController(){
		// set the headers for authentication
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-api-key", "2f7b7e46-b66a-4281-8ac4-821cf1a03efb");
		entityReq = new HttpEntity<String>(headers);		
	}
	
	// Returns an exact match of the artists name
	// To return a List, we need to change the return type to Iterable<Artist> and return the "artistsJson" object instead of "result"
	@GetMapping(path="/artist")
	public @ResponseBody Artist getArtist (@RequestParam(required=true) String artistName) {
				
		String request = "https://api.setlist.fm/rest/1.0/search/artists?";
		Artist result;

		if (artistName != null && !artistName.equals("")){
			request += "artistName="+artistName+"&";
		}		
				
		System.out.println("request: "+request);		
		
		RestTemplate restTemplate = new RestTemplate();

		//actual service call
		ResponseEntity<ArtistsJson> artistsJsonEntity = (ResponseEntity<ArtistsJson>) restTemplate.exchange(request, HttpMethod.GET, entityReq, ArtistsJson.class);	
		System.out.println("artistsJsonEntity.getStatusCode: "+artistsJsonEntity.getStatusCode());

		//get the main JSON object from the entity
		ArtistsJson artistsJson = (ArtistsJson) artistsJsonEntity.getBody();
		
		// look for only the artist with the exact name
		result = findExactMatch(artistsJson.getArtist(), artistName);

		System.out.println("artistsJson: "+artistsJson);

		return result; //artistsJson.response();
	}
	
	public Artist findExactMatch(ArrayList<Artist> artistList, String name){
		for (Artist artist: artistList){
			if (name.equalsIgnoreCase(artist.getName())){
					return artist;
			}
		}
		return null;
	}
}