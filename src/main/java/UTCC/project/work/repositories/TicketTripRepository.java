package UTCC.project.work.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import UTCC.project.work.model.TicketTrip;
import io.lettuce.core.dynamic.annotation.Param;

public interface TicketTripRepository extends CrudRepository<TicketTrip, Long>{

    @Query(value = "SELECT * FROM ticket_trip WHERE worksheet_id = :worksheetId and trip =  (SELECT MAX(trip) FROM ticket_trip WHERE worksheet_id = :worksheetId)  ", nativeQuery = true)
    TicketTrip findWorksheetById(@Param("worksheetId") Long worksheetId);
    
}
