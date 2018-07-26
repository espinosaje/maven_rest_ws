package hello;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import hello.Event;
import hello.EventRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

	Iterable<Event> findByName(String Name);	//added later to add the query

	//SELECT l.artist, v.name, e.year FROM event e, venue v, lineup l WHERE e.id=l.event and e.venue=v.id and e.id =
	@Query("select e from Event e where e.id = ?1")
    Iterable<Event> findBySearchTerm(Integer searchTerm);

}