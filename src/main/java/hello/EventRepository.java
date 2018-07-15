package hello;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import hello.Event;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete

public interface EventRepository extends CrudRepository<Event, Long> {

	Iterable<Event> findByName(String Name);	//added later to add the query
}