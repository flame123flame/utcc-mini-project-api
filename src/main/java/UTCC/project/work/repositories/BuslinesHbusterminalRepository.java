package UTCC.project.work.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BuslinesHbusterminal;
import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface BuslinesHbusterminalRepository extends CrudRepository<BuslinesHbusterminal, Long>{
	
    @Query(value = "SELECT * FROM buslines_h_busterminal WHERE bus_lines_id = :busLinesId", nativeQuery = true)
    List<BuslinesHbusterminal> findByBusLinesId(@Param("busLinesId") long busLinesId);
    
}
