package hello.venue;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.web.bind.annotation.RequestParam;
import hello.venue.Venue;

import org.springframework.web.bind.annotation.GetMapping;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete

public interface VenueRepository extends JpaRepository<Venue, Long> {

	Venue findById(Integer id);
	Venue findByName(String name);
	Venue findBySetlistId(String setlistId);
	
	//SELECT l.artist, v.name, e.year FROM event e, venue v, lineup l WHERE e.id=l.event and e.venue=v.id and e.id =
	@Query("select c from Venue c where (?1 is null or c.id = ?1) and (?2 is null or ?2='' or c.name = ?2) and (?3 is null or ?3='' or c.setlistId = ?3)")
    Iterable<Venue> search(Integer id, String name, String setlistId);
}