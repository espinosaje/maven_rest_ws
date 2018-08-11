package hello.lineup;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.web.bind.annotation.RequestParam;
import hello.lineup.Lineup;

import org.springframework.web.bind.annotation.GetMapping;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete

public interface LineupRepository extends JpaRepository<Lineup, Long> {

	Iterable<Lineup> findByEvent(String Event);	//added later to add the query

	//@Query("select e from Lineup e where e.id = ?1")
    //Iterable<Lineup> findBySearchTerm(Integer searchTerm);

}