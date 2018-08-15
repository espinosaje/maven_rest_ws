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

	Iterable<Venue> findByName(String Name);	//added later to add the query


}